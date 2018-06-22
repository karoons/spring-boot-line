package com.ananda.service;

public interface LineMessagingService {
    void handleMessage(String requestBodyFromLine, String xLineSignature) throws Exception;
    boolean isRequestBodyFromLine(String requestBody , String xLineSignature) throws Exception;
}