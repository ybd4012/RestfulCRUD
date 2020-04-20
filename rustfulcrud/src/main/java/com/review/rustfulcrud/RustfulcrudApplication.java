package com.review.rustfulcrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
/**
 * 通过包扫描 将mapper加入到容器中
 */
@MapperScan(value = "com.review.rustfulcrud.mapper")
/**
 * 开启基于缓存的注解
 */
@EnableCaching
public class RustfulcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RustfulcrudApplication.class, args);
    }

}
