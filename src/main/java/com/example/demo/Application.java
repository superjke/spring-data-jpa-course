package com.example.demo;

import com.github.javafaker.Faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@email.com", firstName, lastName);

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            student.addBook(new Book("Clean Code", LocalDateTime.now()));
            student.addBook(new Book("Think and Grow Rich", LocalDateTime.now().minusDays(2)));
            student.addBook(new Book("Rich Dad Poor Dad", LocalDateTime.now().minusYears(1)));

            StudentIdCard studentIdCard = new StudentIdCard("123456789", student);

            student.setStudentIdCard(studentIdCard);
            studentRepository.save(student);

            studentRepository.findById(1L).ifPresent(s -> {
                List<Book> books = s.getBooks();
                books.forEach(book -> {
                    System.out.println(s.getFirstName() + " borrowed " + book.getBookName());
                });
            });
        };
    }

    private void generateStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();

        for (int i = 0; i <= 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@email.com", firstName, lastName);

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            studentRepository.save(student);
        }
    }

}
