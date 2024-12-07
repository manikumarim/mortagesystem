package com.interview.hcl.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MortgagesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MortgagesystemApplication.class, args);
    }

}
