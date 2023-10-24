package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Bookings")
public class Bookings implements Serializable {

    @Id
    @Column(name = "booking_id")
    Integer BookingId;

    @Column(name = "booking_date")
    Timestamp BookingDate;
    
    @Column(name = "checkin_date")
    Timestamp CheckinDate;
    
    @Column(name = "checkout_date")
    Timestamp CheckoutDate;
    
    @Column(name = "payment_status")
    Boolean PaymentStatus;
    
    @Column(name = "total_price", precision = 10, scale = 2) 
    BigDecimal TotalPrice;

    @ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels hId;
    
    @ManyToOne
	@JoinColumn(name = "payment_id")
	Payment payId;
    
    @ManyToOne
	@JoinColumn(name = "CMT")
	Users UserId;
}
