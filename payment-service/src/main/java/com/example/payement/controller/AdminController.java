package com.example.payement.controller;


import com.example.payement.service.IPayment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/payment")
public class AdminController {
    private IPayment iPayment;

    //validate payment
    @GetMapping("/validatePayment/{id}")
    public ResponseEntity validatePayment(@PathVariable Long id){
        iPayment.validatepayment(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //decline payment
    @GetMapping("/declinePayment/{id}")
    public ResponseEntity declinePayment(@PathVariable Long id){
        iPayment.declinepayment(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //test
    @GetMapping("/test")
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

}
