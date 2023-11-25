package com.poly.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Hotels")
public class Hotels implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "hotel_id")
	Integer id;

	@Column(name = "hotel_name")
	String name;

	@Column(name = "address")
	String address;

	@Column(name = "phone_number")
	String phone;

	@Column(name = "Email_hotel")
	String email;

	@Column(name = "description", columnDefinition = "TEXT")
	String description;
	
//	@Column(name = "a", columnDefinition = "TEXT")
//	String a;

	@Column(name = "hotel_level")
	Integer Level;

	@ManyToOne
	@JoinColumn(name = "place_id")
	Places Place;

//	@ManyToOne
//	@JoinColumn(name = "hotel_type_id")
//	HotelTypes HotelTypes;

	@JsonIgnore
	@OneToMany(mappedBy = "Hotels")
	List<Rooms> Rooms;

	@JsonIgnore
	@OneToMany(mappedBy = "Hotels")
	List<Images_Hotel> Images_Hotel;

	@JsonIgnore
	@OneToMany(mappedBy = "Hotels")
	List<Booking_Room> Booking_Room;
}
