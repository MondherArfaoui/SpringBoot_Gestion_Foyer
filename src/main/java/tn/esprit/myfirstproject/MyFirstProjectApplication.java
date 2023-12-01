package tn.esprit.myfirstproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.myfirstproject.entities.Admin;
import tn.esprit.myfirstproject.entities.Role;
import tn.esprit.myfirstproject.entities.User;
import tn.esprit.myfirstproject.repositories.IAdminRepository;
import tn.esprit.myfirstproject.repositories.IUserRepository;

@RequiredArgsConstructor
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class MyFirstProjectApplication implements CommandLineRunner {

	private final IUserRepository userRepository;
	private final IAdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null) {
			Admin admin = new Admin();
			admin.setEmail("admin@gmail.com");
			admin.setNom("admin");
			admin.setPrenom("admin");
			admin.setRole(Role.ADMIN);
			admin.setImage("ffffffff");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			adminRepository.save(admin);
		}
	}
}
