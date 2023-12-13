package com.poly.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Bookings")
public class Bookings implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	Integer id;

	@Column(name = "payment_status")
	Boolean Status;

	@Column(name = "booking_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate BookingDate;

	@Column(name = "checkin_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate  CheckinDate;

	@Column(name = "checkout_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate  CheckoutDate;
	
	@Transient
    private String bookingDateStr;

    @Transient
    private String checkinDateStr;

    @Transient
    private String checkoutDateStr;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	Payment Payment;

	@ManyToOne
	@JoinColumn(name = "cmt")
	Users Users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "Bookings")
	List<Booking_Room> Booking_Room;
}
