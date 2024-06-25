package com.service.controller;

import com.service.model.Order;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProcessingServiceController {
    @PostMapping(path = "/orders", consumes = MediaType.ALL_VALUE)
    public String processOrders(@RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> {
          System.out.println(String.format("Header '%s' = %s", key, value));
        });

        return "";
    }
}
