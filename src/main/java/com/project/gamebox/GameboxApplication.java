package com.project.gamebox;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameboxApplication.class, args);
	}
	
	public ModelMapper modelmapper()
	{
		return new ModelMapper();
	}

}
