package com.axis.caravela.security.control;

import com.axis.caravela.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial user
        createUserIfNotFound("test@test.com", "admin", "admin", "admin", "admin");

        // == create initial user technician
        createUserIfNotFound("user@user1.com", "user1", "user1", "user1", "admin");

        alreadySetup = true;
    }


    @Transactional
    User createUserIfNotFound(final String email, final String userName, final String firstName, final String lastName, final String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
            user.setAvailable(true);
            user.setRegisterDate(LocalDate.now());
            user.setColor("#3f51b5");
        }

        user = userRepository.save(user);

        return user;
    }

}