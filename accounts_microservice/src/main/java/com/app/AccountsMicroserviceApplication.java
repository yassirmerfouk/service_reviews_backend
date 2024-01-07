package com.app;

import com.app.model.Role;
import com.app.model.User;
import com.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AccountsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				/*userRepository.save(new User(null,"admin@gmail.com", passwordEncoder.encode("123456"), Role.ADMIN));*/
			}
		};
	}
}
