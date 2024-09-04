package kr.co.iei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //해당 클래스가 Bean을 사용한다고 알려주는 어노테이션
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Bean
	public BCryptPasswordEncoder bcrypt() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry
			.addResourceHandler("/**")
			.addResourceLocations("Classpath:/templates/", "Classpath:/static/"); // css 넣은 경로 설정
	}
}
