package tech.saintbassanaga.liveasy_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LiveasyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveasyTestApplication.class, args);
    }

}
