package com.axis.caravela.security.control;

import com.axis.caravela.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUserName(String userName);

    @Override
    void delete(User user);


}
