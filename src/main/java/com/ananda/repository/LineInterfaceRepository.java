package com.ananda.repository;

import org.json.JSONObject;

import java.util.Map;

public interface LineInterfaceRepository {
    Map<String, Object> replayMessage(String replyToken,JSONObject jsonData) throws Exception;
}
