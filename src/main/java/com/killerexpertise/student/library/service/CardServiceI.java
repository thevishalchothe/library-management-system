package com.killerexpertise.student.library.service;

import com.killerexpertise.student.library.model.Card;
import com.killerexpertise.student.library.model.Student;

public interface CardServiceI {
    Card createCard(Student student);
    void deactivate(int studentId);
}
