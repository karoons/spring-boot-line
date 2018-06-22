package com.ananda.service;

import com.ananda.config.ConfigurationProperties;
import com.ananda.exception.LineException;
import com.ananda.helper.Track;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
//import static com.ananda.helper.Track;

@Service("CustomSdkLineMessagingService")
public class CustomSdkLineMessagingService implements LineMessagingService {

    @Autowired
    ConfigurationProperties configurationProperties;

    @Autowired
    @Qualifier("JacksonJsonMapperService")
    JsonMapperService JsonMapperService;

    @Autowired
    Track trackingOutput;



    @Override
    public void handleMessage(String requestBodyFromLine, String xLineSignature) throws Exception {

        if (this.isRequestBodyFromLine(requestBodyFromLine, xLineSignature)) {
            Map<String,Object> result =  JsonMapperService.fromJson(requestBodyFromLine, Map.class);
            trackingOutput.printMapDynamic(result);
        } else {
            System.out.println("----------------This Request body not verified ! [" + requestBodyFromLine + "]");
//            throw new LineException("this Request body not verified ! [" + requestBodyFromLine + "]");
        }

    }

    @Override
    public boolean isRequestBodyFromLine(String requestBody, String xLineSignature) throws Exception {
        String channelSecret = configurationProperties.getLine_channel_secret();
//        String channelAccessToken = configurationProperties.getLine_channel_access_token();

        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] source = requestBody.getBytes("UTF-8");
        String signature = Base64.encodeBase64String(mac.doFinal(source));
        if (signature.length() == xLineSignature.length()) {
            return true;
        } else {
            return false;
        }
    }

}
