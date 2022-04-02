package com.example.zuccecho.entry;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "teacher",schema = "zuccecho",catalog = "")
public class Teacher implements Serializable {

    @Id
    @Column(name = "teacherId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int teacherId;

    @Column(name = "pwd")
    public String pwd;

    @Column(name = "account")
    public String account;

    @Column(name = "teacherName")
    public String teacherName;

    @Column(name = "phone")
    public String phone;
}
