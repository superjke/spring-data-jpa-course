package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "student", uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique", columnNames = "email")
})
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Getter
    @Setter
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Getter
    @Setter
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Getter
    @Setter
    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "student", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @Setter
    private StudentIdCard studentIdCard;

    @Getter
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.PERSIST,
            CascadeType.REMOVE })
    private List<Book> books = new ArrayList<>();

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", age=" + age + "]";
    }

}
