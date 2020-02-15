package com.example.demo.webserivce;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

    //需要查看wsdl文件
    public static void main(String[] args) throws MalformedURLException {
        URL wsdlUrl = new URL("http://localhost:8080/jdkwsdemo/demo.JdkWebService?wsdl");
        Service s = Service.create(wsdlUrl, new QName("http://webserivce.demo.example.com/","JdkWebServiceService"));
        WebService hs = s.getPort(new QName("http://webserivce.demo.example.com/","JdkWebServicePort"), WebService.class);
        String ret = hs.doSomething("zhangsan");
        System.out.println(ret);
    }


}
