package com.example.zuccecho.entry;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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

    @JsonIgnore
    @ManyToMany(mappedBy = "zuccClasses")
    public Set<Student> students = new HashSet<>();

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
