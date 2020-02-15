package com.example.demo;

import com.example.demo.anno.PushAnno;

public interface PushService {

    @PushAnno(type="家庭作业",who="张三") //用注解配置
    void pushHomework( String content );

    @PushAnno(type="考勤",who="李四")
    void pushAttendToPerson(String date , String door,String demesion);

    @PushAnno(type="考勤",who="家长")
    void pushAttendToParent(String date , String door,String demesion);


}
