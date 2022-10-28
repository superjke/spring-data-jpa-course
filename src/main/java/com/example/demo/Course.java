package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_sequence", sequenceName = "course_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_sequence")
    @Column(name = "id", updatable = false)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    @Getter
    @Setter
    private String name;

    @Column(name = "department", nullable = false, columnDefinition = "TEXT")
    @Getter
    @Setter
    private String department;

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", department=" + department + "]";
    }

}
