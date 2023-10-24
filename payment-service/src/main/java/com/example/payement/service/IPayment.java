package com.example.payement.service;

import com.example.payement.entity.StatusType;
import com.example.payement.entity.payment;

import java.util.List;

public interface IPayment {

    public payment addpayment(payment payment);
    public payment updatepayment(payment payment);
    public payment getpaymentByid(Long id); // get details of a payment
    public List<payment> getpayments(); // get details of a payment
    public void validatepayment(Long id);
    public void declinepayment(Long id);

}
