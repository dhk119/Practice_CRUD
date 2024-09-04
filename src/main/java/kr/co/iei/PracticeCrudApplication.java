package kr.co.iei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude -> 해당 기본설정을 사용하지 않음을 선언
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})//스프링 부트에 자동설정되어 있는거를 해제
public class PracticeCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeCrudApplication.class, args);
	}

}
