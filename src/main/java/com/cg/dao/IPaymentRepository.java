package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

	// public Optional<Payment> addPayment(Payment payment);

	// public Optional<Payment> removePayment(long id);

	// public Optional<Payment> updatePayment(long id,Payment payment);

	// public Optional<Payment> getPayment(long id);

}
