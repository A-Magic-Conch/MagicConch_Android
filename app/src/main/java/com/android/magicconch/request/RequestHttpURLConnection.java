package com.android.magicconch.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;

public class RequestHttpURLConnection {
    public String request(int[][] params) {
        String urlStr = "http://203.234.62.169:5000/";
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(urlStr);
            urlConn = (HttpURLConnection) url.openConnection();
            // [2-1]. HttpURLConnection 설정.
            urlConn.setConnectTimeout(15000);
            urlConn.setReadTimeout(5000);
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/json");
            // [2-2]. JSON 배열 생성 및 전송.
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < params.length; i++) {
                JSONArray innerArray = new JSONArray();
                for (int j = 0; j < params[i].length; j++) {
                    innerArray.put(params[i][j]);
                }
                jsonArray.put(innerArray);
            }
            // 데이터 전송
            OutputStream os = urlConn.getOutputStream();
            os.write(jsonArray.toString().getBytes("UTF-8"));
            os.flush();
            os.close();
            // [2-3]. 연결 확인.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;
            // [2-4]. 서버 응답 읽기.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            StringBuilder page = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                page.append(line);
            }
            return page.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }
        return null;
    }
}
