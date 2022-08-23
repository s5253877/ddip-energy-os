package com.ddip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

@SpringBootApplication
@MapperScan("com.ddip.dao")
public class DdipEnergyOsApplication {
    public static void main(String[] args) {
        SpringApplication.run( DdipEnergyOsApplication.class, args );
    }
}