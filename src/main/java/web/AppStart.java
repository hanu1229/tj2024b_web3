package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

// 스프링부트 기초 설정
@SpringBootApplication
// JPA 감사 기능 활성화
@EnableJpaAuditing
// 스케줄링 기능 활성화
@EnableScheduling
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
