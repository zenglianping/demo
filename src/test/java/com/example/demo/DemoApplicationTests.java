package com.example.demo;

import com.example.demo.scope.AppConfig;
import com.example.demo.scope.ScopeBean;
import com.example.demo.scope.TestScope;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableFeignClients
//相当于让spring生成一个EurekaClient对象
@EnableEurekaClient
@EnableDiscoveryClient
public class DemoApplicationTests {

    private Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private SayI sayI;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PushService pushService;


    @Autowired
    ApplicationContext context;

    @Autowired
    AppConfig appConfig;

    @Autowired
    TestScope testScope;

    @Test
    public void testScope() {
        ScopeBean scopeBean = (ScopeBean) context.getBean("scopeBean");
        logger.info("scopeBean.getScopeName:{}", scopeBean.getScopeName());
        //模拟配置信息更改
        appConfig.setName("config changed !");
        //清空缓存
        testScope.destroy();
        //重新获取bean
        scopeBean = (ScopeBean) context.getBean("scopeBean");
        //发现修改生效
        logger.info("scopeBean.getScopeName:{}", scopeBean.getScopeName());

    }

    @Test
    public void testAutoImpl() {
        pushService.pushHomework("1+1=?");
        pushService.pushAttendToPerson("2019-12-08 20:30:30", "东门", "进入");
        pushService.pushAttendToParent("2019-12-08 20:30:30", "东门", "进入");
    }

    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("demo-provider", false);
        return instance.getHomePageUrl();
    }

    @Test
    public void contextLoads() {
//        String serviceUrl = serviceUrl();
        String providerAppName = "demo-provider";
        String sayHi = restTemplate.getForObject("http://" + providerAppName + "/", String.class);
        logger.info("sayHi:" + sayHi);
        Assert.assertEquals("Hello world", sayHi);


    }


    @Test
    public void testFeign() {
        String sayHi = sayI.say();
        logger.info("sayHi=====:" + sayHi);
        Assert.assertEquals("Hello world", sayHi);


    }

    @Test
    public void testLoadBalance() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("demo-provider");
        String serviceUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        logger.info("serviceUrl:" + serviceUrl);
//        int i = 0 ;
//        while (i < 10) {
//            i++;
//            serviceInstance = loadBalancerClient.choose("demo-provider");
//            serviceUrl ="http://"+ serviceInstance.getHost()+":"+serviceInstance.getPort();
//            logger.info("serviceUrl:"+serviceUrl);
//        }
        String sayHi = restTemplate.getForObject(serviceUrl, String.class);
        logger.info("sayHi:" + sayHi);
        Assert.assertEquals("Hello world", sayHi);
    }


}
