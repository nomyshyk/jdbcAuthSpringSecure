package kg.it.secure.jdbcauth.boot;

import kg.it.secure.jdbcauth.entity.User;
import kg.it.secure.jdbcauth.entity.UserRoles;
import kg.it.secure.jdbcauth.repository.UserRepository;
import kg.it.secure.jdbcauth.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstraper implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user1 = new User("alish",
                passwordEncoder.encode("123"), 1);
        userRepository.save(user1);

        User user2 = new User("another",
                passwordEncoder.encode("456"), 1);
        userRepository.save(user2);

        User user3 = new User("disabledUser",
                passwordEncoder.encode("789"), 0);
        userRepository.save(user3);

        UserRoles userRoles1 = new UserRoles(user1, "ROLE_ADMIN");
        userRolesRepository.save(userRoles1);

        UserRoles userRoles2 = new UserRoles(user2, "ROLE_USER");
        userRolesRepository.save(userRoles2);

        UserRoles userRoles3 = new UserRoles(user3, "ROLE_USER");
        userRolesRepository.save(userRoles3);

    }
}
