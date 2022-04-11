package com.example.zuccecho.entry;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


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

    @ManyToMany(targetEntity = ZuccClass.class,cascade = CascadeType.ALL)
    @JoinTable(name = "course",joinColumns = {@JoinColumn(name = "stuId",referencedColumnName = "stuId")},
    inverseJoinColumns = {@JoinColumn(name = "classId",referencedColumnName = "classId")})
    public Set<ZuccClass> zuccClasses = new HashSet<>();

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStuAccount() {
        return stuAccount;
    }

    public void setStuAccount(String stuAccount) {
        this.stuAccount = stuAccount;
    }

    public Set<ZuccClass> getZuccClasses() {
        return zuccClasses;
    }

    public void setZuccClasses(Set<ZuccClass> zuccClasses) {
        this.zuccClasses = zuccClasses;
    }
}
