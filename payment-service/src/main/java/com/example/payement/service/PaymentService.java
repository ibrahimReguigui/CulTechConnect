package com.example.payement.service;

import com.example.payement.entity.StatusType;
import com.example.payement.entity.payment;
import com.example.payement.repository.PayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPayment {

    @Autowired
    private PayRepo payRepo;

    @Override
    public payment addpayment(payment payment) {
        payment.setStatusType(StatusType.PENDING);
       return payRepo.save(payment);
    }

    @Override
    public payment updatepayment(payment payment) {
        payment p = payRepo.findById(payment.getId_payment()).get();
        if (p != null) {
            p.setName_on_Card(payment.getName_on_Card());
            p.setCard_Number(payment.getCard_Number());
            p.setCVV(payment.getCVV());
            p.setMM(payment.getMM());
            p.setYY(payment.getYY());
            p.setCountry(payment.getCountry());
            p.setStatusType(payment.getStatusType());
            p.setUser(payment.getUser());
            System.out.println(p);
            return payRepo.save(p);
        }
        else
            throw new RuntimeException("payment not found");
    }
    @Override
    public payment getpaymentByid(Long id) {
       if (payRepo.findById(id).isPresent()){
                return payRepo.findById(id).get();
            }
            else
                throw new RuntimeException("payment not found");
    }

    @Override
    public List<payment> getpayments() {
        if (payRepo.findAll().isEmpty()){
            throw new RuntimeException("No payment found");
        }
        else
            return payRepo.findAll();
    }

    @Override
    public void validatepayment(Long id) {
        if (payRepo.findById(id).isPresent()){

            payment p = payRepo.findById(id).get();
            p.setStatusType(StatusType.ACCEPTED);
            payRepo.save(p);
        }
        else
            throw new RuntimeException("payment not found");

    }

    @Override
    public void declinepayment(Long id) {
        if (payRepo.findById(id).isPresent()){
            payment p = payRepo.findById(id).get();
            p.setStatusType(StatusType.DECLINED);
            payRepo.save(p);
        }
        else
            throw new RuntimeException("payment not found");

    }
}
