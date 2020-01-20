package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/getCode")
    public String getCode( HttpServletRequest request) {
        Map<String, String[]> paraM = request.getParameterMap();;

        paraM.entrySet().stream().forEach( entry ->{
            logger.info("key:"+entry.getKey()+"-------->"+"value:"+entry.getValue());
        });

        return "CODE";

    }

}
