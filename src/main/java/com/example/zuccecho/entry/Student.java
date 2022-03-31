package com.example.zuccecho.entry;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student", schema = "zuccecho", catalog = "")
public class Student {
    @Id
    @Column(name = "stuId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int stuId;

    @Column(name = "name")
    public String name;

    @Column(name = "pwd")
    public String pwd;

    @Column(name = "phone")
    public String phone;

}