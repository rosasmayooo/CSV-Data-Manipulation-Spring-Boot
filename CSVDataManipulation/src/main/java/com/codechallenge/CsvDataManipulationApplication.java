package com.codechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.codechallenge.service.RecordService;


@SpringBootApplication
@EntityScan("com.codechallenge.model")
@EnableJpaRepositories("com.codechallenge.repository")
public class CsvDataManipulationApplication {

	@Autowired
	private final RecordService recordService;
	
	public static void main(String[] args) {
		
		SpringApplication.run(CsvDataManipulationApplication.class, args);
		
    }
	
	public CsvDataManipulationApplication(RecordService recordService) {
        this.recordService = recordService;
    }
	
	@Bean
    public ApplicationRunner init() {
        return args -> {
        	recordService.saveRecord();
        };
    }
}
