package com.example.zuccecho.entry;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "class", schema = "zuccecho",catalog = "")
public class ZuccClass {

    @Id
    @Column(name = "classId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int classId;

    @Column(name = "tea_teacherId")
    public int teacherId;

    @Column(name = "className")
    public String className;

    @Column(name = "createTime")
    public Date createTime;
}
