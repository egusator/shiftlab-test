package com.example.shiftlabtest;

import com.example.shiftlabtest.repository.model.Desktop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

@SpringBootApplication
public class ShiftlabTestApplication {
    private JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {

        SpringApplication.run(ShiftlabTestApplication.class, args);
    }

}
