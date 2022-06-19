package com.axis.minagri.security.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlanningUser implements Serializable {

    private String id;
    private String title;
    private String eventBackgroundColor;
}
