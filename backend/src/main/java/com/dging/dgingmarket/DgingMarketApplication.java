package com.dging.dgingmarket;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@EnableJpaAuditing(dateTimeProviderRef = "localDateTimeProvider")
@SpringBootApplication
@RequiredArgsConstructor
public class DgingMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(DgingMarketApplication.class, args);
	}

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
