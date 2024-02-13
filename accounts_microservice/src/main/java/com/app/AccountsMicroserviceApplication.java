package com.app;

import com.app.model.BusinessAccount;
import com.app.model.PersonnelAccount;
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
				if(userRepository.findAll().isEmpty()){
					userRepository.save(new User(null,"admin@gmail.com", passwordEncoder.encode("123456"), Role.ADMIN));
					for(int i=1;i<=4;i++){
						userRepository.save(
								PersonnelAccount.builder()
										.email("yassirmerfouk"+i+"@gmail.com")
										.password(passwordEncoder.encode("123456"))
										.role(Role.PERSONNEL)
										.firstName("Yassir "+i)
										.lastName("Merfouk "+i)
										.build()
						);
					}
					for(int i=1;i<=4;i++){
						userRepository.save(
								BusinessAccount.builder()
										.email("yassirmerfoukbusiness"+i+"@gmail.com")
										.password(passwordEncoder.encode("123456"))
										.role(Role.BUSINESS)
										.address("resid. Abou Rai 3, 3° et, Grand Casablanca, Grand Casablanca")
										.description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
										.employeesNumber("1 - 50 employees")
										.name("Yassir Merfouk "+i)
										.build()
						);
					}
				}
			}
		};
	}
}
