package io.httpClient;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: HttpUrlConnectTest
 * Package: io.httpClient
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/22 18:50
 * @Version 1.0
 */
public class HttpUrlConnectTest {
    // 使用java原生api来请求网页
    @Test
    public void test01() throws IOException {
        String urlString = "https://www.baidu.com";
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

        // 获取HttpUrlConnect的输入流
        try(
                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ) {
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
