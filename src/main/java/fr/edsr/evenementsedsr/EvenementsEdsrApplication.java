package fr.edsr.evenementsedsr;

import fr.seblaporte.springsocial.EnableOauth2SocialLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOauth2SocialLogin
public class EvenementsEdsrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvenementsEdsrApplication.class, args);
	}

}
