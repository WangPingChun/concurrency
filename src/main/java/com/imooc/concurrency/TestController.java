package com.imooc.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chris
 * 2018-07-04
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
