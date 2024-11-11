package com.capstoneproj.passenger_service;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients

public class PassengerServiceApplication {

	public PassengerServiceApplication() {

	}

	public static void main(String[] args) {

		SpringApplication.run(PassengerServiceApplication.class, args);

	}

}

