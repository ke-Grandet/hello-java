package org.example.provider;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("org.example.provider.mapper")
public class ProviderApplication {

    public static void main(String[] arguments) {

        SpringApplication.run(ProviderApplication.class, arguments);

    }
}