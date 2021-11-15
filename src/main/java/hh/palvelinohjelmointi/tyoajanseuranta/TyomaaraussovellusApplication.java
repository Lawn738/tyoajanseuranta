package hh.palvelinohjelmointi.tyoajanseuranta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.palvelinohjelmointi.tyoajanseuranta.domain.Task;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.TaskRepository;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.User;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.UserRepository;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.Workday;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.WorkdayRepository;

@SpringBootApplication
public class TyomaaraussovellusApplication {
	private static final Logger log = LoggerFactory.getLogger(TyomaaraussovellusApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TyomaaraussovellusApplication.class, args);
	}@Bean
	public CommandLineRunner tyomaaraussovellusappi(WorkdayRepository wRepository, TaskRepository pRepository, UserRepository userRepository) {
		return (args) -> {
			
			// Luo käyttäjät: admin/admin, user1/user1, user2/user2
			log.info("Add users");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userRepository.save(new User("user1", encoder.encode("user1"), "user1@user.com","USER"));
			userRepository.save(new User("user2", encoder.encode("user2"), "user2@user.com","USER"));
			userRepository.save(new User("admin", encoder.encode("admin"), "admin@admin.com","ADMIN"));
			
			log.info("Fetch all users");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			
			
			log.info("save workdays");
			
			pRepository.save(new Task("Työnnä Kärryjä", "user1"));
			pRepository.save(new Task("Työnnä Lisää Kärryjä", "user1"));
			pRepository.save(new Task("Vie roskat", "user2"));
			pRepository.save(new Task("testi", "admin"));
			
			wRepository.save(new Workday("5.5.2021", 6.5, "8.00", "15.25", "user1", pRepository.findByName("Työnnä Kärryjä").get(0)));
			wRepository.save(new Workday("5.5.2021", 5.5, "7.00", "17.00", "user1", pRepository.findByName("Työnnä Lisää Kärryjä").get(0)));	
			wRepository.save(new Workday("5.5.2021", 8.0, "6.00", "14.00", "user2", pRepository.findByName("Vie roskat").get(0)));	
			
			log.info("fetch all workdays");
			for (Workday workday : wRepository.findAll()) {
				log.info(workday.toString());
			}

		};
	}
}