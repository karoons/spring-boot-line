/*
 * Copyright 2018 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ananda.interceptor;

import com.ananda.annotation.LineBotMessages;
//import com.linecorp.bot.model.event.CallbackRequest;
//import com.linecorp.bot.servlet.LineBotCallbackException;
//import com.linecorp.bot.servlet.LineBotCallbackRequestParser;
//import com.linecorp.bot.spring.boot.annotation.LineBotMessages;
//import com.linecorp.bot.spring.boot.support.LineBotServerArgumentProcessor;
//import lombok.extern.slf4j.Slf4j;
import com.ananda.model.event.CallbackRequest;
import com.ananda.objectmapper.ModelObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class LineBotServerInterceptor implements HandlerInterceptor {
//    private final ObjectMapper objectMapper = ModelObjectMapper.createNewObjectMapper();
//    @Autowired
//    private LineBotCallbackRequestParser lineBotCallbackRequestParser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("-------------------------------preHandle----------------------------: ");
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod hm = (HandlerMethod) handler;
//        MethodParameter[] methodParameters = hm.getMethodParameters();
//        for (MethodParameter methodParameter : methodParameters) {
////            System.out.println("-------------------------------preHandle----------------------------: "+methodParameter.getParameterName());
//            if (methodParameter.getParameterAnnotation(LineBotMessages.class) != null) {
//                try {
//                    System.out.println("------------------------------- "+request.getContextPath());
//                    final byte[] json = ByteStreams.toByteArray(request.getInputStream());
//                    String jsonString = new String(json, StandardCharsets.UTF_8);
//                    System.out.println("----------------- "+jsonString);
//                    final CallbackRequest callbackRequest = objectMapper.readValue(json, CallbackRequest.class);
//                    System.out.println("ObjectMapping ----------------- "+callbackRequest.toString());
//
////                    CallbackRequest callbackRequest = lineBotCallbackRequestParser.handle(request);
////                    LineBotServerArgumentProcessor.setValue(request, callbackRequest);
//                    return true;
//
//                } catch (Exception e) {
////                    log.info("LINE Bot callback exception: {}", e.getMessage());
//                    response.sendError(HttpStatus.BAD_REQUEST.value());
////                    try (PrintWriter writer = response.getWriter()) {
////                        writer.println(e.getMessage());
////                    }
//                    return false;
//                }
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println("----------------postHandle--"+modelAndView.toString());
//        System.out.println("-------------------------------postHandle----------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex)
            throws Exception {
//        System.out.println("-------------------------------afterCompletion----------------------------");
    }
}
