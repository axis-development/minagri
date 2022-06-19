package com.axis.minagri.security.control;

import com.axis.minagri.security.entity.User;
import com.axis.minagri.security.entity.UserAlreadyExistException;
import com.axis.minagri.security.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Talkaiss on 30/12/2017.
 */
@Service
@Transactional
public class UserService {

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    public User registerNewUserAccount(final UserDTO accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setUserName(accountDto.getUserName());
        user.setEmail(accountDto.getEmail());
        user.setRegisterDate(LocalDate.now());
        user.setColor(accountDto.getColor());
        user.setAddress(accountDto.getAddress());
        user.setComments(accountDto.getComments());
        user.setEmailNotification(accountDto.isEmailNotification());
        user.setEnabled(accountDto.isEnabled());
        user.setJob(accountDto.getJob());
        user.setMobile(accountDto.getMobile());
        user.setPlaningVisible(accountDto.isVisibleInPlaning());
        user.setLastLoginDate(LocalDateTime.now());
        user.setPhone(accountDto.getPhone());

        return userRepository.save(user);
    }


    public User modifyUser(final UserDTO accountDto) {
        final User user = findUserById(accountDto.getId());

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setUserName(accountDto.getUserName());
        user.setEmail(accountDto.getEmail());
        //user.setRoles(Arrays.asList(roleRepository.findByValue()));
        user.setColor(accountDto.getColor());
        user.setAddress(accountDto.getAddress());
        user.setComments(accountDto.getComments());
        user.setEmailNotification(accountDto.isEmailNotification());
        user.setJob(accountDto.getJob());
        user.setMobile(accountDto.getMobile());
        user.setPlaningVisible(accountDto.isVisibleInPlaning());
        user.setLastLoginDate(LocalDateTime.now());
        user.setPhone(accountDto.getPhone());
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findUserById(final Long id) {
        Optional<User> op = userRepository.findById(id);
        return op.isPresent() ? op.get() : null;
    }

    public UserDTO findUser(Long id) {
        User user = (User) entityManager.createQuery("select u from User u where u.id =:id")
                .setParameter("id", id)
                .getSingleResult();
        return mapUser(user);
    }



    public Map<String, Object> getUserByUsername(String username) {
        final User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        //logic here to get your user from the database
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("username", user.getUserName());
        userMap.put("password", user.getPassword());
        userMap.put("email", user.getEmail());
        userMap.put("firstName", user.getFirstName());
        userMap.put("lastName", user.getLastName());

        //if username is admin, role will be admin, else role is user only
        userMap.put("role", (username.equals("admin")) ? "admin" : "user");
        //return the usermap
        return userMap;
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDTO> findAllUsers() {
        List<UserDTO> ret = new ArrayList<>();
        Query query = entityManager.createQuery("select u from User u order by userName");
        List<User> users = (List<User>) query.getResultList();
        users.forEach(user -> {
                    ret.add(mapUser(user));
                }
        );

        return ret;
    }

    public User getCurrentUser() {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    public UserDTO mapUser(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setId(user.getId());
        userDto.setName(user.getFirstName() + " " + user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRegisterDate(user.getRegisterDate());
        userDto.setComments(user.getComments());
        userDto.setColor(user.getColor());
        List<String> privileges = new ArrayList<>();
        userDto.getPrivileges().addAll(privileges);

        return userDto;
    }




    @org.springframework.transaction.annotation.Transactional
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}