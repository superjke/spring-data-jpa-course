package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Entity
public class Student {

    @Getter @Setter
    @Id
    private Long id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private Integer age;

    public Student(Long id, String firstName, String lastName, String email, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }
}
