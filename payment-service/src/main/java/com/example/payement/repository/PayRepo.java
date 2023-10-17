package com.example.payement.repository;
import com.example.payement.entity.payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepo extends JpaRepository<payment, Long> {
}
