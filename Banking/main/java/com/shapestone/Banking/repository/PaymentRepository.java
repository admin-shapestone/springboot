package com.shapestone.Banking.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shapestone.Banking.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

	List<Payment> findByAccountid(int accountid);

//List<Payment> findByAccountid();

}
