package com.shapestone.Banking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shapestone.Banking.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	Optional<Account> findByAccountid(int accountid);

}
