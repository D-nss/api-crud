package com.dnss.api_ti9;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="Swagger OpenApi", version ="'", description = "API desenvolvida para testes do OpenApi"))
public class ApiTi9Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiTi9Application.class, args);
	}

}
