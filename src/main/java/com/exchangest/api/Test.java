package com.exchangest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class Test {

    @GetMapping("test")
    public String test() {
        return "test";
    }

}
