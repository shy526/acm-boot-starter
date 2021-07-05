package com.github.qing;

import com.github.qing.autoconfigure.AcmProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qing
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
/*    @Autowired
    AcmProperties acmProperties;*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
