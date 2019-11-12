package joce.practice.concurrency.st.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
public class HttpUtils {
    private final static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static <T> T doPost(String url, String body, Map<String, String> headers, Class<T> klass) throws IOException {
        final HttpPost httpPost = new HttpPost();
        headers.forEach((k, v) -> {
            httpPost.addHeader(k, v);
        });
        httpPost.setURI(URI.create(url));
        final StringEntity httpEntity = new StringEntity(body);
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            return transferToResult(response, klass);
        } finally {
            response.close();
            httpclient.close();
        }
    }

    public static <T, V> T doPost(String url, V body, Map<String, String> headers, Class<T> klass) throws IOException {
        return doPost(url, JsonUtils.toJson(body), headers, klass);
    }

    public static <T> T doPost(String url, Map<String, String> nameValues, Map<String, String> headers, Class<T> klass) throws IOException {
        final HttpPost httpPost = new HttpPost();
        headers.forEach((k, v) -> {
            httpPost.setHeader(k, v);
        });
        httpPost.setURI(URI.create(url));
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nameValues.forEach((k, v) -> {
            nvps.add(new BasicNameValuePair(k, v));
        });
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            return transferToResult(response, klass);
        } finally {
            response.close();
            httpclient.close();
        }
    }

    public static <T> T doGet(String url, HashMap<String, String> headers, Class<T> klass) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        headers.forEach((k, v) -> {
            httpGet.addHeader(k, v);
        });
        final CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            return transferToResult(response, klass);
        } finally {
            response.close();
            httpclient.close();
        }
    }

    private static <T> T transferToResult(CloseableHttpResponse response, Class<T> klass) throws IOException {
        final StatusLine statusLine = response.getStatusLine();
        if (200 == statusLine.getStatusCode()) {
            HttpEntity entity = response.getEntity();
            return JsonUtils.toJson(entity.getContent(), klass);
        }
        return null;
    }
}
