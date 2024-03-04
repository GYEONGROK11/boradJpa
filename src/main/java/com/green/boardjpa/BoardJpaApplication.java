package com.green.boardjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@EnableJpaAuditing // JPA를 사용하기 위한 설정
@ConfigurationPropertiesScan // yaml 파일을 사용하기 위한 설정
@SpringBootApplication
public class BoardJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardJpaApplication.class, args);
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p -> p.setOneIndexedParameters(true); // 1부터 시작하는 페이지 번호 사용 (0도, 1도 1페이지임)
	}


}
