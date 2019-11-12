package joce.practice.concurrency.st.entity;

import lombok.Data;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
@Data
public class UserAccount {
    private String username;
    private String password;
    private String refreshToken;
    private String exchangeToken;
    private String accessToken;
}
