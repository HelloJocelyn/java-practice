package joce.practice.concurrency.st;

import joce.practice.concurrency.st.entity.*;
import joce.practice.concurrency.st.entity.enums.GrantType;
import joce.practice.concurrency.st.entity.enums.TokenType;
import joce.practice.concurrency.st.utils.HttpUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
public class NewAddressBookApiTest {
    private static final String REFRESHTOKEN_POST = "http://localhost:9090/api/v1/multi";
    private static final String ACCESS_TOKEN_POST = "http://127.0.0.1:3000/v1/oauth/tokens?grant_type=exchange_token";
    private static final String ADDRESSES_GET = "http://localhost:3000/v1/addresses";
    private static final String SPLITOR = ":";
    private static final Map<String, String> COMMON_HEADERS = new HashMap<String, String>() {
        {
            put("Content-Type", "application/json");
        }
    };
    private LinkedBlockingQueue queue1 = new LinkedBlockingQueue<UserAccount>();
    private LinkedBlockingQueue queue2 = new LinkedBlockingQueue<UserAccount>();
    private LinkedBlockingQueue queue3 = new LinkedBlockingQueue<UserAccount>();

    public static void main(String[] args) {

        NewAddressBookApiTest newAddressBookApiTest = new NewAddressBookApiTest();
        newAddressBookApiTest.startSTTest();

    }

    private void startSTTest() {
        // 1. load user accounts: 50
        try {
            final List<String> lines = Files.readAllLines(Paths.get("./conf","accounts.txt"));
            lines.parallelStream().forEach(line -> {
                final String[] split = line.split(SPLITOR);
                final UserAccount userAccount = new UserAccount();
                userAccount.setUsername(split[0].trim());
                userAccount.setPassword(split[1].trim());
                userAccount.setRefreshToken(getRefreshToken(userAccount));
                userAccount.setExchangeToken(getExchangeToken(userAccount));
                // 2. generate refresh token,exchane token and access-token
                final String accessToken = getAccessToken(userAccount);
                userAccount.setAccessToken(accessToken);
                // set user accounts to 3 queues
                try {
                    queue1.put(userAccount);
                    queue2.put(userAccount);
                    queue3.put(userAccount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            });
        } catch (IOException e) {
            System.out.println("Failed to load accounts file");
            System.exit(1);
        }

        // start 50 threads to test
        final ExecutorService executorService = Executors.newCachedThreadPool();
        Callable task1 = () -> {
            return executeGetAddresses(queue1);
        };
        Callable task2 = () -> {
            return executeGetAddresses(queue1);
        };
        Callable task3 = () -> {
            return executeGetAddresses(queue1);
        };
        final List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String executeGetAddresses(LinkedBlockingQueue<UserAccount> queue) {
        int totalTimes = 3;
        int eachTimes = 100;
        HashMap<String, String> headers = new HashMap<>();
        headers.put("x-forwarded-for", "10.80.74.165");
        for (int i = 0; i < totalTimes; i++) {
            while (eachTimes > 0) {
                final UserAccount userAccount = queue.poll();

                headers.put("Authorization", "Bearer " + userAccount.getAccessToken());

                try {
                    HttpUtils.doGet(ADDRESSES_GET, headers, Addresses.class);
                    queue.put(userAccount);
                    eachTimes--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "finished";
    }

    private String getAccessToken(UserAccount userAccount) {
        MultiRequestBody multiRequestBody = new MultiRequestBody(GrantType.UserPassword.getValue(), TokenType.REFRESH.getValue());
        Grant grant = multiRequestBody.getGrant();
        grant.setUsername(userAccount.getUsername());
        grant.setPassword(userAccount.getPassword());


        try {
            MultiResponseBody refreshToken = HttpUtils.doPost(REFRESHTOKEN_POST, multiRequestBody, COMMON_HEADERS, MultiResponseBody.class);
            MultiRequestBody requestExchange = new MultiRequestBody(GrantType.TokenRefresh.getValue(), TokenType.EXCHANGE.getValue(), "address_book");
            requestExchange.getGrant().setValue(refreshToken.getArtifacts().getTokenRefresh().getToken());
            MultiResponseBody exchangeToken = HttpUtils.doPost(REFRESHTOKEN_POST, requestExchange, COMMON_HEADERS, MultiResponseBody.class);
            final HashMap<String, String> nameValues = new HashMap<>();
            nameValues.put("exchange_token", exchangeToken.getArtifacts().getTokenExchange().getToken());
            final AccessToken accessToken = HttpUtils.doPost(ACCESS_TOKEN_POST, nameValues, COMMON_HEADERS, AccessToken.class);
            return accessToken.getAccess_token();

        } catch (IOException e) {
            System.out.println("Failed to get token for : " + grant.getUsername());
        }

        return null;
    }

    private String getExchangeToken(UserAccount userAccount) {
        return null;
    }

    private String getRefreshToken(UserAccount userAccount) {
        return null;
    }
}
