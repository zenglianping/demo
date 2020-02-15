package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("demo-provider")
public interface SayI {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String say ();
}
