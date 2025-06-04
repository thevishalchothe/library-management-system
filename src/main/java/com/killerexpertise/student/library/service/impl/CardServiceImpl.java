package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.model.Card;
import com.killerexpertise.student.library.model.CardStatus;
import com.killerexpertise.student.library.model.Student;
import com.killerexpertise.student.library.repository.CardRepository;
import com.killerexpertise.student.library.service.CardServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardServiceI {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card createCard(Student student) {
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);
        student.setCard(card);
        return cardRepository.save(card);
    }

    @Override
    public void deactivate(int studentId) {
        // Find card by student ID
        Card card = cardRepository.findByStudentId(studentId);
        if (card != null) {
            card.setCardStatus(CardStatus.DEACTIVATED);
            cardRepository.save(card);
        }
    }
}
