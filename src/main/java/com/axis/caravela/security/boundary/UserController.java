package com.axis.caravela.security.boundary;

import com.axis.caravela.security.control.UserService;
import com.axis.caravela.security.entity.User;
import com.axis.caravela.security.entity.UserDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Tahar on 04/11/2016.
 */
@Controller
public class UserController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/user/viewUser")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "user/viewUser";
    }

    @RequestMapping(value = "/user/fetchUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> fetchUsers() throws Exception {
        return userService.findAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/saveUser")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity saveUser(@RequestBody UserDTO userDto) {
        try {
            userService.registerNewUserAccount(userDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userDto);

        } catch (DataIntegrityViolationException e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("The User " + userDto.getUserName() + " already exist");
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(userDto);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/editUser")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity editUser(@RequestBody UserDTO userDto) {
        try {
            userService.modifyUser(userDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userDto);

        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(userDto);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/removeUser/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity removeUser(@PathVariable String id) {

        try {
            userService.removeUser(Long.valueOf(id));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(id);
        } catch (Exception e) {
            logger.error(e);
            if (e instanceof PersistenceException) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("This Team is linked to an user !!");
            }
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when try to delete the User !!");
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/findUser/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity findUser(@PathVariable String id) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userService.findUser(Long.valueOf(id)));
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when try to find the user " + id);
        }

    }

    @RequestMapping(value = "/user/currentUser", method = RequestMethod.GET)
    @ResponseBody
    public User currentUser() throws Exception {
        return userService.getCurrentUser();
    }

}
