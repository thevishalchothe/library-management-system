package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.service.TransactionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceI transactionServiceI;

    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestParam int cardId, @RequestParam int bookId) throws Exception {
        String txnId = transactionServiceI.issueBooks(cardId, bookId);
        return new ResponseEntity<>("Book issued. Transaction ID: " + txnId, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam int cardId, @RequestParam int bookId) throws Exception {
        String txnId = transactionServiceI.returnBooks(cardId, bookId);
        return new ResponseEntity<>("Book returned. Transaction ID: " + txnId, HttpStatus.OK);
    }
}
