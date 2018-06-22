package com.ananda.repository;

import com.ananda.config.ConfigurationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Repository("RestApiRepository")
public class RestApiRepository implements LineInterfaceRepository {

    @Autowired
    ConfigurationProperties configurationProperties;

    @Override
    public Map<String, Object> replayMessage(String replyToken, JSONObject jsonData) throws Exception {
        try{

            Map<String, Object> result = new HashMap<>();

            URL url = new URL(configurationProperties.getLine_api_url_message_reply());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "Bearer  " + configurationProperties.getLine_channel_access_token());

            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(jsonData.toString().getBytes("UTF-8"));
            os.flush();
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), Charset.forName("UTF-8")));
                String output;
                StringBuilder response = new StringBuilder();

                while ((output = br.readLine()) != null) {

                    response.append(output);
                    ObjectMapper mapper2 = new ObjectMapper();
                    result = mapper2.readValue(output, result.getClass());
                    System.out.println("result " +result);

                }
            } else {
                System.out.println("-------  Reply message has fail ------ "+conn.getResponseCode()+" message "+conn.getResponseMessage() );

            }

            os.close();
            conn.disconnect();
            result.forEach((k, v) -> System.out.println("Key: " + k + "Value: " + v));

            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
