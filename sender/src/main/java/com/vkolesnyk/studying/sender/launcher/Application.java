package com.vkolesnyk.studying.sender.launcher;

import com.vkolesnyk.studying.sender.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ImportAutoConfiguration(ApplicationConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.vkolesnyk.studying.sender")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
