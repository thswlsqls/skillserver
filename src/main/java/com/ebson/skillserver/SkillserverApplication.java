package com.ebson.skillserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SkillserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillserverApplication.class, args);
	}

}
