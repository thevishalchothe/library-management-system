package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.service.TransactionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceI transactionServiceI;

    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestParam int cardId, @RequestParam int bookId) throws Exception {
        String txnId = transactionServiceI.issueBooks(cardId, bookId);
        return ResponseEntity.ok("Book issued. Transaction ID: " + txnId);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam int cardId, @RequestParam int bookId) throws Exception {
        String txnId = transactionServiceI.returnBooks(cardId, bookId);
        return ResponseEntity.ok("Book returned. Transaction ID: " + txnId);
    }
}
