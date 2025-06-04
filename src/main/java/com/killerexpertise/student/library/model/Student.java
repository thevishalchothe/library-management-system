package com.killerexpertise.student.library.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emailId;

    private String name;

    private int age;

    private String country;

    @OneToOne
    @JoinColumn
    private Card card;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    public Student(String emailId, String name, int age, String country) {
        this.emailId = emailId;
        this.name = name;
        this.age = age;
        this.country = country;
    }
}
