package br.com.projeto.politicalmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition
@EnableFeignClients
public class PoliticalManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoliticalManagementApplication.class, args);
    }

}
