package com.example.plazoleta.ms_plazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.plazoleta.ms_plazoleta.infrastructure.client")
public class MsPlazoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPlazoletaApplication.class, args);
	}

}
