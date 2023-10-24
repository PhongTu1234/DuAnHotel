package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Payments")
public class Payment implements Serializable {

    @Id
    @Column(name = "payment_id")
    Integer PaymentId;

    @Column(name = "payment_date")
    Timestamp payment_date;
    
    @Column(name = "total_amount", precision = 10, scale = 2) 
    BigDecimal TotalAmount;
    
    @Column(name = "payment_method")
    String PaymentMethod;
    
    @JsonIgnore
	@OneToMany(mappedBy = "payId")
	List<Bookings> BookingId;
    
}