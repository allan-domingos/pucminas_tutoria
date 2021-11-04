package br.com.mineradora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 10 de abr. de 2021
 */
@EnableScheduling
@SpringBootApplication
public class SisAqApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SisAqApplication.class, args);
	}

}
