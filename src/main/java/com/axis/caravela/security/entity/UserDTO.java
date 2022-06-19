package com.axis.caravela.security.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;
    private String userName;
    private String name;
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;
    private LocalDate registerDate;
    private boolean visibleInPlaning;
    private List<String> teams = new ArrayList<>();
    private List<String> privileges = new ArrayList<>();
    private String comments;
    private String color;
    private boolean emailNotification;
    private Long team;
    private String teamName;
    private String job;
    private String mobile;
    private String phone;
    private String address;
    private boolean enabled;
}
