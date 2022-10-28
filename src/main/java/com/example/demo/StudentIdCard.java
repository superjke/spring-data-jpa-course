package com.example.demo;

import lombok.Getter;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card", uniqueConstraints = {
                @UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")
})
public class StudentIdCard {

        @Id
        @SequenceGenerator(name = "student_card_id_sequence", sequenceName = "student_card_id_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_card_id_sequence")

        @Column(name = "id", updatable = false)
        @Getter
        private Long id;

        @Column(name = "card_number", nullable = false, length = 15)
        @Getter
        private String cardNumber;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "student_id_fk"))
        private Student student;

        public StudentIdCard(String cardNumber) {
                this.cardNumber = cardNumber;
        }

        public StudentIdCard(String cardNumber, Student student) {
                this.cardNumber = cardNumber;
                this.student = student;
        }

        public StudentIdCard() {
        }

        @Override
        public String toString() {
                return "StudentIdCard [id=" + id + ", cardNumber=" + cardNumber + "]";
        }

}
