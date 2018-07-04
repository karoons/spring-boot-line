package com.ananda.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class MessagingControllerTest {

        /*
    mvn -Dtest=MessagingControllerTest test
    */

    private MockMvc mvc;

    @InjectMocks
    private MessagingController messagingController;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(messagingController).build();
    }

    @Test
    public void testGetHeaderOfRequestor() throws Exception{
        MockHttpServletResponse response = mvc.perform(
                get("/api/messaging/header")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        System.out.println("----- "+response.getContentAsString());
        System.out.println("---------------   Test -------");
    }

}
