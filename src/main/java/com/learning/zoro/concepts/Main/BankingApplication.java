package com.learning.zoro.concepts.Main;

import com.learning.zoro.concepts.Service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan("com.learning.zoro.*")
@EnableJpaRepositories("com.learning.zoro.concepts.Repository")
@EntityScan("com.learning.zoro.concepts.*")
public class BankingApplication implements CommandLineRunner {

    @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
