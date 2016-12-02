package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a couple of Users
			repository.save(new User("Jack", "Bauer"));
			repository.save(new User("Chloe", "O'Brian"));
			repository.save(new User("Kim", "Bauer"));
			repository.save(new User("David", "Palmer"));
			repository.save(new User("Michelle", "Dessler"));

			// fetch all Users
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User User : repository.findAll()) {
				log.info(User.toString());
			}
			log.info("");

			// fetch an individual User by ID
			User User = repository.findOne(1L);
			log.info("User found with findOne(1L):");
			log.info("--------------------------------");
			log.info(User.toString());
			log.info("");

			// fetch Users by last name
			log.info("User found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (User bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}
