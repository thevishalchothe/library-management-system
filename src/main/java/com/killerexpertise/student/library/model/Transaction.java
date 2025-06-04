package com.killerexpertise.student.library.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn
    private Card card;

    private int fineAmount;

    @ManyToOne
    @JoinColumn
    private Book book;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isIssueOperation;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;
}
