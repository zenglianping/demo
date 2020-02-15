package com.example.demo.webserivce;


import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class JdkWebService implements com.example.demo.webserivce.WebService {

    public String doSomething(String value) {
        return "Just do it," + value + "!";
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/jdkwsdemo/demo.JdkWebService", new JdkWebService());
        System.out.println("发布完成！！");

    }
}
