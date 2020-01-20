package com.example.demo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@EnableFeignClients
public class DemoApplicationTests {

    private Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    private SayI sayI ;

    @Autowired
    TestRestTemplate testRestTemplate ;

    @Autowired
    TestInvoke testInvoke ;

    @Autowired
    TestScope testScope ;

    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("demo-provider", false);
        return instance.getHomePageUrl();
    }

    @Test
    public void contextLoads() {
        String url = serviceUrl();
        logger.info( "serviceUrl======"+url);
        String sayHi = testRestTemplate.getForObject(url, String.class);
        logger.info("sayHi:"+sayHi);
        Assert.assertEquals("Hello world",sayHi);


    }

    @Test
    public void testFeign() {
        String sayHi = sayI.say();
        logger.info("sayHi=====:"+sayHi);
        Assert.assertEquals("Hello world",sayHi);


    }

    @Test
    public void testScope() {

        testInvoke.getTestBean();

        testScope.destroy();

        testInvoke.getTestBean();


    }



}
