package com.example.zuccecho.form;


import com.example.zuccecho.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;


@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto implements Serializable {

    private final Integer stuId;
    private final String name;
    private final String phone;
    private final String email;


}
