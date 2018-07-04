/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ananda.controller;

import com.ananda.annotation.LineBotMessages;
import com.ananda.model.event.Event;
import com.ananda.service.LineMessagingService;
import com.ananda.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Karoons
 */
@RestController
@RequestMapping("/api/adv_messaging")
public class AdvMessagingController {

    @PostMapping("/callback")
//    public void callback(@LineBotMessages List<Event> events) {
    public void callback(@RequestBody Map<String,Object> events) {
        System.out.println("------ "+events);
//        for (Event e:events) {
//            System.out.println("-----------------------------"+e.getSource());
//        }
    }

}
