package xyz.n490808114.shopWeb;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpClientTest {
    public static void main(String[] args) {


        HttpGet httpGet = new HttpGet("http://www.baidu.com/s?wd=12306");

        HttpClient client = HttpClients.createDefault();

        HttpResponse response = null;
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String resp = EntityUtils.toString(entity,"utf8");
            System.out.println("[[resp]]:"+resp);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
