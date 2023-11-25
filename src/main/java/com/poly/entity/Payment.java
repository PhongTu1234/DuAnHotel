package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Payments")
public class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	Integer id;

	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate date;
	
	@Transient
    private String paymentDateStr;

	@Column(name = "total_amount", precision = 10, scale = 2)
	BigDecimal Total;

	@Column(name = "payment_method")
	String Method;

	@JsonIgnore
	@OneToMany(mappedBy = "Payment")
	List<Bookings> Bookings;

}