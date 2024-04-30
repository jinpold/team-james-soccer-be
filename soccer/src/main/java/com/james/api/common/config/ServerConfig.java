package com.james.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;

@Configuration
public class ServerConfig {
    @Bean
    public String datePattern(){
        return "yyyy-MM-dd'T'HH:mm:ss.XXX";
    }
    @Bean
    public DateFormatter defaultDateFormatter(){
        return new DateFormatter(datePattern());
    }
}
//configuration @이 선언되면 자바 설정 클래스다.
//@bean을 붙이기전에는 메서드, bean을 붙이는 순간 datepattern메서드는 string에 의존하며, string 객체 취급을 당한다
// ^ 실행이되야 저 개념이 성립, bean 객체는 return 객체에 의존성을 갖고, return 객체 타입을 생성한다