package com.ananda.service;

import com.ananda.config.ConfigurationProperties;
import com.ananda.exception.LineException;
import com.ananda.helper.Track;
import com.ananda.repository.LineInterfaceRepository;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    @Qualifier("RestApiRepository")
    LineInterfaceRepository lineInterfaceRepository;


    @Override
    public void handleMessage(String requestBodyFromLine, String xLineSignature) throws Exception {

        if (this.isRequestBodyFromLine(requestBodyFromLine, xLineSignature)) {
            Map<String, Object> root = JsonMapperService.fromJson(requestBodyFromLine, Map.class);
//            Map<String,Object> result = (Map<String, Object>) root.get("events");
            Iterator it = root.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (pair.getValue().getClass().getTypeName().equals("java.util.ArrayList")) {
                    for (Map<String, Object> _item : (ArrayList<Map<String, Object>>) pair.getValue()) {
//                        trackingOutput.printMapDynamic(result);
                        if (_item.get("replyToken") != null
                                && !Strings.isNullOrEmpty((String) _item.get("replyToken"))
                                && "message".equalsIgnoreCase((String) _item.get("type"))) {
                            this.autoReplyMessage(_item);
                        } else {
                            System.out.println("not response");
                        }

                    }
                }
            }

        } else {
            System.out.println("----------------This Request body not verified ! [" + requestBodyFromLine + "]");
//            throw new LineException("this Request body not verified ! [" + requestBodyFromLine + "]");
        }

    }

    @Override
    public void handleMessageByMap(Map<String, Object> requestBody, String xLineSignature) throws Exception {

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

    @Override
    public boolean isRequestBodyFromLineByMap(Map<String, Object> requestBody, String xLineSignature) throws Exception {
        return false;
    }


    private void autoReplyMessage(Map<String, Object> messageFromLine) throws Exception {

        String replyToken = null;
        String message_id = null;
        String message_type = null;
        String message_text = null;

        String source_type = null;
        String soutce_userId = null;

        if (messageFromLine.get("replyToken") != null && !Strings.isNullOrEmpty((String) messageFromLine.get("replyToken"))) {
            replyToken = (String) messageFromLine.get("replyToken");
        }

        if (messageFromLine.get("message") != null) {
            Map<String, Object> messageObject = (Map<String, Object>) messageFromLine.get("message");
            message_id = (String) messageObject.get("id");
            message_type = (String) messageObject.get("type");
            message_text = (String) messageObject.get("text");
        }

        if (messageFromLine.get("source") != null) {
            System.out.println(messageFromLine.get("source"));
            Map<String, Object> sourceObject = (Map<String, Object>) messageFromLine.get("source");
            source_type = (String) sourceObject.get("type");
            soutce_userId = (String) sourceObject.get("userId");
        }

        JSONObject rootObject = new JSONObject();
        rootObject.put("replyToken", replyToken);


        JSONArray items = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("type", "text");
        item.put("text", "HI " + message_text);

        items.put(item);
        item = new JSONObject();
        item.put("type", "text");
        item.put("text", "HI " + message_text);
        items.put(item);
        rootObject.put("messages", items);

        System.out.println("rootObject =========== " + rootObject.toString());

        lineInterfaceRepository.replayMessage(replyToken, rootObject);


    }

}
