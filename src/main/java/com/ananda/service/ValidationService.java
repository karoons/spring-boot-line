package com.ananda.service;

import com.ananda.config.ConfigurationProperties;
import com.ananda.model.event.MessageEvent;
import com.ananda.model.event.message.TextMessageContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

@Service
public class ValidationService {
    @Autowired
    ConfigurationProperties configurationProperties;

    @Autowired
    @Qualifier("jackSon")
    ObjectMapper mapper;

    public void signatureValidation(String requestBody, String xLineSignature) throws Exception {
        String channelSecret = configurationProperties.getLine_channel_secret();
        String channelAccessToken = configurationProperties.getLine_channel_access_token();

//        Jackson2ObjectMapperBuilder.

        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] source = requestBody.getBytes("UTF-8");
        String signature = Base64.encodeBase64String(mac.doFinal(source));
        System.out.println("----requestBody : " + requestBody);
        System.out.println("---signature : " + signature);
        System.out.println("---xLineSignature : " + xLineSignature);
        if (signature.length() == xLineSignature.length()) {
            System.out.println("------ size OK ------");

//            MessageEvent<TextMessageContent> restponseData
//                    = jackson2ObjectMapperBuilder.

//            ObjectMapper

        }
        if (signature.equalsIgnoreCase(xLineSignature)) {
            System.out.println("------ contain OK ------");
        }
    }

}
