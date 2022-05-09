package com.cg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Payment;
import com.cg.dao.IPaymentRepository;

@Service
public class IPaymentService {

	@Autowired
	private IPaymentRepository payRepository;

	public Optional<Payment> addPayment(Payment payment) {
		payRepository.save(payment);
		return payRepository.findById(payment.getPaymentId());

	}

	public Optional<Payment> removePayment(long id) {
		Optional<Payment> payment = payRepository.findById(id);
		payRepository.deleteById(id);
		return payment;
	}

	public Optional<Payment> updatePayment(long id, Payment payment) {
		Payment pay = payRepository.findById(id).get();
		pay.setPaymentMethod(payment.getPaymentMethod());
		pay.setAmountPaid(payment.getAmountPaid());
		payRepository.save(pay);

		return payRepository.findById(id);
	}

	public Optional<Payment> getPayment(long id) {
		return payRepository.findById(id);
	}

}
