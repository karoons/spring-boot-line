package com.ananda.service;

import java.util.Map;

public interface LineMessagingService {
    void handleMessage(String requestBodyFromLine, String xLineSignature) throws Exception;
    void handleMessageByMap(Map<String,Object> requestBody, String xLineSignature) throws Exception;
    boolean isRequestBodyFromLine(String requestBody , String xLineSignature) throws Exception;
    boolean isRequestBodyFromLineByMap(Map<String,Object> requestBody , String xLineSignature) throws Exception;
}