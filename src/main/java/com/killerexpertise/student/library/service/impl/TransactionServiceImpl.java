package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.exception.BookUnavailableException;
import com.killerexpertise.student.library.exception.CardInvalidException;
import com.killerexpertise.student.library.exception.TransactionNotFoundException;
import com.killerexpertise.student.library.model.*;
import com.killerexpertise.student.library.repository.BookRepository;
import com.killerexpertise.student.library.repository.CardRepository;
import com.killerexpertise.student.library.repository.TransactionRepository;
import com.killerexpertise.student.library.service.TransactionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionServiceImpl implements TransactionServiceI {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Value("${books.max_allowed}")
    private int maxAllowedBooks;

    @Value("${books.max_allowed_days}")
    private int maxAllowedDays;

    @Value("${books.fine.per_day}")
    private int finePerDay;

    @Override
    public String issueBooks(int cardId, int bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookUnavailableException("Book with ID " + bookId + " is not available or doesn't exist."));
        if (!book.isAvailable()) {
            throw new BookUnavailableException("Book with ID " + bookId + " is already issued.");
        }

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardInvalidException("Card with ID " + cardId + " does not exist."));
        if (card.getCardStatus() == CardStatus.DEACTIVATED) {
            throw new CardInvalidException("Card with ID " + cardId + " is deactivated.");
        }

        if (card.getBooks().size() >= maxAllowedBooks) {
            throw new CardInvalidException("Card with ID " + cardId + " has reached the book limit.");
        }

        book.setAvailable(false);
        book.setCard(card);
        card.getBooks().add(book);
        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setIsIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }

    @Override
    public String returnBooks(int cardId, int bookId) {
        List<Transaction> transactions = transactionRepository.findAll().stream()
                .filter(txn -> txn.getCard().getId() == cardId &&
                        txn.getBook().getId() == bookId &&
                        txn.getTransactionStatus() == TransactionStatus.SUCCESSFUL &&
                        txn.getIsIssueOperation())
                .toList();

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No valid issue transaction found for card ID " + cardId + " and book ID " + bookId);
        }

        Transaction lastIssueTransaction = transactions.get(transactions.size() - 1);
        Date issueDate = lastIssueTransaction.getTransactionDate();
        long issueTime = Math.abs(issueDate.getTime() - System.currentTimeMillis());
        long daysPassed = TimeUnit.DAYS.convert(issueTime, TimeUnit.MILLISECONDS);

        int fine = 0;
        if (daysPassed > maxAllowedDays) {
            fine = (int) (daysPassed - maxAllowedDays) * finePerDay;
        }

        Book book = lastIssueTransaction.getBook();
        Card card = lastIssueTransaction.getCard();

        book.setCard(null);
        book.setAvailable(true);
        bookRepository.save(book);

        Transaction returnTxn = new Transaction();
        returnTxn.setCard(card);
        returnTxn.setBook(book);
        returnTxn.setIsIssueOperation(false);
        returnTxn.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        returnTxn.setFineAmount(fine);
        returnTxn.setTransactionDate(new Date());

        transactionRepository.save(returnTxn);
        return returnTxn.getTransactionId();
    }
}
