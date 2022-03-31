package com.example.zuccecho.entry;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher",schema = "zuccecho",catalog = "")
public class Teacher {

    @Id
    @Column(name = "teacherId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int teacherId;

    @Column(name = "teacherName")
    public String teacherName;

    @Column(name = "phone")
    public String phone;
}
