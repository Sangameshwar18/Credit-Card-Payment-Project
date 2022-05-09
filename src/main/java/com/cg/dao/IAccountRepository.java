package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bean.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

	// Get All Accounts for a cutomer
	@Query(value = "SELECT a FROM Account a JOIN a.customers c WHERE c.id = ?1")
	public List<Account> findAllAccountsById(long id);
}
