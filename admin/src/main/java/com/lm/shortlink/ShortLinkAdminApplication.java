package com.lm.shortlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.lm.shortlink.admin.dao.mapper")
public class ShortLinkAdminApplication {

    public static void main(String[] arg){
        SpringApplication.run(ShortLinkAdminApplication.class,arg);
    }
}
