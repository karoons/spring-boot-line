/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ananda.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 *
 * @author Karoons
 */
@RestController
@RequestMapping("/api/messaging")
public class MessagingController {

    @RequestMapping(value = "/your/ok", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public Object currentStatus() throws Exception {
        return "this service is ok";
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public void lineWebHook(HttpServletRequest req) throws Exception {
        System.out.println("EVENT 2--- -"+req.getHeader("X-Line-Signature"));
//        return "this service is ok";
    }

    
}
