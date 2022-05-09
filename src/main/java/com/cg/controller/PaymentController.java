//package com.cg.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cg.bean.Payment;
//import com.cg.service.IPaymentService;
//
//@RestController
//public class PaymentController {
//
//	@Autowired
//	private IPaymentService payService;
//
//	@PostMapping("/addNewPayment")
//	public Optional<Payment> addPayment(@RequestBody Payment payment) {
//		return payService.addPayment(payment);
//	}
//
//	@DeleteMapping("/removePayment/{id}")
//	public Optional<Payment> removePayment(@PathVariable("id") long payId) {
//		return payService.removePayment(payId);
//	}
//
//	@PutMapping("/updatePayment/{id}")
//	public Optional<Payment> updatepayment(@PathVariable("id") long payId, @RequestBody Payment payment) {
//		return payService.updatePayment(payId, payment);
//	}
//
//	@GetMapping("/getPayment/{id}")
//	public Optional<Payment> getPayment(@PathVariable("id") long payId) {
//		return payService.getPayment(payId);
//	}
//
//}
