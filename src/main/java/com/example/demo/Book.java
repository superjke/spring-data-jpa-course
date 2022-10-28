package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Book")
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(name = "book_id_sequence", sequenceName = "book_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
    @Column(name = "id", updatable = false)
    @Getter
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_id_fk"))
    @Getter
    @Setter
    private Student student;

    @Column(name = "book_name", nullable = false)
    @Getter
    private String bookName;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Getter
    private LocalDateTime createdAt;

    public Book(Student student, String bookName, LocalDateTime createdAt) {
        this.student = student;
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Book(String bookName, LocalDateTime createdAt) {
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", bookName=" + bookName + ", createdAt=" + createdAt + "]";
    }

}
