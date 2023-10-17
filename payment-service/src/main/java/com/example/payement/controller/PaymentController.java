package com.example.payement.controller;

import com.example.payement.service.IPayment;
import com.example.payement.entity.payment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/payment")

public class PaymentController {
    private IPayment iPayment;
    //add payment
    @PostMapping("/addPayment")
    public payment addPayment(@RequestBody payment payment ){
        payment addedPayment = iPayment.addpayment(payment);
        System.out.println("payment added"+payment);
        return addedPayment;
    }

    //update payment
    @PutMapping("/updatePayment")
    public ResponseEntity<String> updatePayment(@RequestBody payment payment ){
        payment updatedPayment = iPayment.updatepayment(payment);
        System.out.println("payment updated");
        return new ResponseEntity<String>("payment updated", HttpStatus.OK);
    }

    //get payment by id
    @GetMapping("/getPaymentByid/{id}")
    public ResponseEntity<payment> getPayment(@PathVariable Long id ){
        payment payment = iPayment.getpaymentByid(id);
        System.out.println("payment found");
        return new ResponseEntity<payment>(payment, HttpStatus.OK);
    }

    //get all payments
    @GetMapping("/getPayments")
    public ResponseEntity<Iterable<payment>> getPayments(){
        Iterable<payment> payments = iPayment.getpayments();
        System.out.println("payments found");
        return new ResponseEntity<Iterable<payment>>(payments, HttpStatus.OK);
    }

    //test
    @GetMapping("/test")
    public String test(){
        return "test";
    }


















}
