/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ananda.controller;

import com.ananda.service.LineMessagingService;
import com.ananda.service.ValidationService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.stream.Collectors;

/**
 *
 * @author Karoons
 */
@RestController
@RequestMapping("/api/messaging")
public class MessagingController {

    @Autowired
    ValidationService validationService;

    @Autowired
    @Qualifier("CustomSdkLineMessagingService")
    LineMessagingService lineMessagingService;

    @RequestMapping(value = "/your/ok", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public Object currentStatus() throws Exception {
        return "this service is ok";
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public void lineWebHook(HttpServletRequest req) throws Exception {
        System.out.println("-- ");
        lineMessagingService.handleMessage(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())),req.getHeader("X-Line-Signature"));
    }

    
}
