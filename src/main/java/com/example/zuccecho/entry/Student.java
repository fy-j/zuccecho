package com.example.zuccecho.entry;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student", schema = "zuccecho", catalog = "")
public class Student implements Serializable {
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

    @Column(name = "stuAccount")
    public String stuAccount;
}
