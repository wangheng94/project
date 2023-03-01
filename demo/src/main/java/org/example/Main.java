package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * $todo
 *
 * @author ${USER}
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // 在服务端代码里访问 http 服务时，一般会使用 JDK 的 HttpURLConnection 或者 Apache 的 HttpClient
        // restTemplate
        testHttpURLConnection();
    }

    private static void testHttpURLConnection() {
        try {
            // 1、访问地址 url
            URL url = new URL("https://www.nipic.com/");
            // 2、网络访问对象 java.net.HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 3、设置请求参数（过期时间，输入，输出流，访问方式），以流的形式进行连接
            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(false);
            // 设置是否向HttpURLConnection读入
            connection.setDoInput(true);
            // 设置请求参数
            connection.setRequestMethod("GET");
            // 设置是否使用缓存
            connection.setUseCaches(true);
            // 设置是否使用缓存
            connection.setUseCaches(true);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置超时时间
            connection.setConnectTimeout(3000);
            // 连接
            connection.connect();
            // 4. 得到响应状态码的返回值 responseCode
            int code = connection.getResponseCode();
            // 5. 如果返回值正常，数据在网络中是以流的形式得到服务端返回的数据
            String msg = "";
            // 正常响应
            if (code == 200) {
                // 从流中读取响应信息
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                // 循环从流中读取
                while ((line = reader.readLine()) != null) {
                    msg += line + "\n";
                }
                reader.close(); // 关闭流
            }
            // 6. 断开连接，释放资源
            connection.disconnect();
            // 显示响应结果
            System.out.println(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}