package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
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

    @Getter
    @Setter
    @OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Enrolment> enrolments = new ArrayList<>();

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Course() {
    }

    public void addEnrolment(Enrolment enrolment) {
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment) {
        this.enrolments.remove(enrolment);
    }

    @Override
    public String toString() {
        return "enrolment [id=" + id + ", name=" + name + ", department=" + department + "]";
    }

}
