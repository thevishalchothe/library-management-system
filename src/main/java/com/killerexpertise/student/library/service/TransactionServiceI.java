package com.killerexpertise.student.library.service;

public interface TransactionServiceI {
    String issueBooks(int cardId, int bookId) throws Exception;

    String returnBooks(int cardId, int bookId) throws Exception;
}
