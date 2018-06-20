package com.ananda.service;

import com.ananda.config.ConfigurationProperties;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

@Service
public class ValidationService {
    @Autowired
    ConfigurationProperties configurationProperties;

    public void signatureValidation(HttpServletRequest req) throws Exception{
        String channelSecret = configurationProperties.getLine_channel_secret();
        String channelAccessToken = configurationProperties.getLine_channel_access_token();

        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
//        byte[] source = req.getBytes("UTF-8");
//        String signature = Base64.encodeBase64String(mac.doFinal(source));
    }

}
