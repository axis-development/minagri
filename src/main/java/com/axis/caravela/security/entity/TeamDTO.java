package com.axis.caravela.security.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jan on 6/11/2016.
 */

@Getter
@Setter
public class TeamDTO implements Serializable {

    private Long id;
    private String name;
    private int userLinked;

}
