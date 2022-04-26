package com.example.zuccecho.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "class", schema = "zuccecho", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "class_id")
public class Clazz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "class_id")
    private int classId;
    @Basic
    @Column(name = "teacher_id")
    private Integer TeacherId;
    @Basic
    @Column(name = "class_name")
    private String className;
    @Basic
    @Column(name = "create_time")
    private Date createTime;

    @ManyToMany(targetEntity = Student.class,cascade = CascadeType.PERSIST)
    @JoinTable(name = "course",
            joinColumns = {@JoinColumn(name = "class_Id",referencedColumnName = "class_Id")},
            inverseJoinColumns = {@JoinColumn(name = "stu_Id",referencedColumnName = "stu_Id")})
    private Set<Student> studentSet = new HashSet<>();

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public Integer getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Integer teacherId) {
        TeacherId = teacherId;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return classId == clazz.classId && TeacherId.equals(clazz.TeacherId) && className.equals(clazz.className) && createTime.equals(clazz.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, TeacherId, className, createTime);
    }
}
