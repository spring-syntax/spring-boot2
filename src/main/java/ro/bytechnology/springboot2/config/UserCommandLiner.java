package ro.bytechnology.springboot2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.bytechnology.springboot2.db.entities.User;
import ro.bytechnology.springboot2.db.repositories.UserRepository;

@Component
public class UserCommandLiner implements CommandLineRunner {

    private static final Logger log = LoggerFactory
            .getLogger(UserCommandLiner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Ranga", "Admin"));
        userRepository.save(new User("Ravi", "User"));
        userRepository.save(new User("Satish", "admin"));
        userRepository.save(new User("Raghu", "User"));

        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }
    }
}
