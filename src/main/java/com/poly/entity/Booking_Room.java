package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Booking_room")
public class Booking_Room implements Serializable {

	@Id
	@Column(name = "bookingroom_id")
	Integer id;

	@Column(name = "Count")
	Integer count;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	Bookings Bookings;

	@ManyToOne
	@JoinColumn(name = "room_id")
	Rooms Rooms;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels Hotels;

}
