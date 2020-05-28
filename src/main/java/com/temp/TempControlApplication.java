package com.temp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TempControlApplication {
	public static void main(String[] args) {
		SpringApplication.run(TempControlApplication.class, args);
	}
}
