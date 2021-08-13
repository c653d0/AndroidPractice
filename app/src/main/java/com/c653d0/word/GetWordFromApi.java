package com.c653d0.word;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class GetWordFromApi {
    private static final String TAG = "connectError";

    public String getResult(String requestUrl, Map params) {

        return httpRequest(requestUrl, params);
    }

    private String httpRequest(String requestUrl, Map params) {
        StringBuilder buffer = new StringBuilder();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(requestUrl + "?" + urlEncode(params));
                    Log.e("requestUrl",url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();

                    //获得输入
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    //将bufferReader的值给放到buffer里
                    String str = null;

                    while ((str = bufferedReader.readLine()) != null) {
                        buffer.append(str);
                    }

                    //关闭bufferReader和输入流
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    inputStream = null;


                    //断开连接
                    httpURLConnection.disconnect();


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "run: "+e.toString());
                }

            }
        });

        thread.start();
        try{
            thread.join(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

    private String urlEncode(Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry i : params.entrySet()) {
            try {
                stringBuilder.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
