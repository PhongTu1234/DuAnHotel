package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Payment;

public interface PaymentDAO extends JpaRepository<Payment, Integer> {
	@Query(value = "SELECT * FROM Payments h  order by h.payment_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Payment> findPageAdmin(Integer page, Integer number);
}
