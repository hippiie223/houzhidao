package com.hippie.houzhidao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hippie.houzhidao.mapper")
public class HouzhidaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouzhidaoApplication.class, args);
    }

}
