/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ananda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Karoons
 */
@Configuration
public class ConfigurationProperties {

    private String line_channel_secret;
    private String line_channel_access_token;
    private final String line_apu_url = "https://api.line.me/v2/bot";
    private String line_api_url_message_reply;


    public String getLine_channel_secret() {
        return "f59fefc3946ea3003f44ea2fbc97e9c3";
    }

    public String getLine_channel_access_token() {
        return "p8xAMjTPGGaY3PmAT5k09gXWr880fP1jAenDN83C0JOzMmDTqBsWwiMJQ5pDogELxAwNyT+7eCzwfBFzbiqq1P6JD4+5kyw921NKUzamisQM9FbcGS0QzRetVfcZezmSPdAPvC7hRiMJgaWfW9qHZQdB04t89/1O/w1cDnyilFU=";
    }

    public String getLine_api_url_message_reply() {
        return line_apu_url + "/message/reply";
    }

    //    @Value("${local.configuration.opcserver.file.location}")
    //    private String dcs_manual_convert_source_path;

}
