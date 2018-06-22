package com.ananda.service;


import com.ananda.model.event.MessageEvent;
import com.ananda.model.event.message.TextMessageContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LineMessagingServiceTest {


    @Autowired
    @Qualifier("CustomSdkLineMessagingService")
    LineMessagingService lineMessagingService;


    //mvn -Dtest=LineMessagingServiceTest#testHandleMessage test
    @Test
    public void testHandleMessage() throws Exception{
       try{
           String requestBody = "{\"events\":[{\"type\":\"message\",\"replyToken\":\"bda16f22f9574ffdae3d258a3e5cc8ac\",\"source\":{\"userId\":\"U8cb26dec1b63f8330a907078be249a7a\",\"type\":\"user\"},\"timestamp\":1529652468822,\"message\":{\"type\":\"text\",\"id\":\"8152255593190\",\"text\":\"5555\"}}]}";
           String xline = "Ump7qq8+7F1fey1ovicsDBUO6Zo2AZGiVn9ov7AXTYQ=";
           lineMessagingService.handleMessage(requestBody,xline);
       }catch (Exception ex){
           ex.printStackTrace();
       }

    }


}
