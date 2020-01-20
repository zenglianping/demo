package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("testScope")
public class TestBean {

    public  String getName (){
        return "testBean";
    }
}
