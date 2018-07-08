package com.imooc.concurrency.example;

import com.imooc.concurrency.example.threadlocal.RequestHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chris
 * 2018-07-08
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test() {
        return RequestHolder.getId();
    }
}
