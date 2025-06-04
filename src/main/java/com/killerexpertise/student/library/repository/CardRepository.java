package com.killerexpertise.student.library.repository;

import com.killerexpertise.student.library.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    // Custom derived query method using Spring Data JPA naming
    Card findByStudentId(int studentId);
}
