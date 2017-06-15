package com.javarush.task.task40.task4002;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Опять POST, а не GET
Реализация должна использовать библиотеки Apache HttpClient версии 4.5.1 и Apache HttpCore 4.4.3.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://requestb.in/152p8g41", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost request = new HttpPost(url);
        request.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> valuePairs = new ArrayList<>();
        String[] parameters = urlParameters.split("&");
        Arrays.stream(parameters).forEach(p ->
                valuePairs.add(new BasicNameValuePair(p.substring(0, p.indexOf("=")), p.substring(p.indexOf("=") + 1)))
        );
        request.setEntity(new UrlEncodedFormEntity(valuePairs));

        HttpResponse response = client.execute(request);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
