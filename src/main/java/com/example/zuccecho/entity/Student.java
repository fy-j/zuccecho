package com.example.zuccecho.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Student implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stu_Id")
    private Integer stuId;
    @Basic
    @Column(name = "account")
    private String account;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "pwd")
    private String pwd;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "studentSet",fetch = FetchType.LAZY)
    private Set<Clazz> clazzes = new LinkedHashSet<>();

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @JsonIgnore
    public Set<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(Set<Clazz> clazzes) {
        this.clazzes = clazzes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return stuId.equals(student.stuId) && account.equals(student.account) && name.equals(student.name) && pwd.equals(student.pwd) && phone.equals(student.phone) && clazzes.equals(student.clazzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, account, name, pwd, phone, clazzes);
    }
}
