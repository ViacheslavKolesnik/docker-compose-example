package com.vkolesnyk.studying.receiver.launcher;

import com.vkolesnyk.studying.receiver.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ImportAutoConfiguration(ApplicationConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.vkolesnyk.studying.receiver")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
