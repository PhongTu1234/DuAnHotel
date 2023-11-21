package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Rooms")
public class Rooms implements Serializable {

	@Id
	@Column(name = "room_id")
	Integer id;

	@Column(name = "roomname")
    String name;
	
	@Column(name = "rating")
    String rate;
    
    @Column(name = "price", precision = 10, scale = 2) 
    BigDecimal price;
    
//    @Column(name = "status", length = 50)
//    String status;

    @Column(name = "soluongphong")
    Integer soluongphong;
    
    @Column(name = "soluongchocheckin")
    Integer soluongchocheckin;
    
    @Column(name = "soluongtrong")
    Integer soluongtrong;
    
    @Column(name = "soluongdangthue")
    Integer soluongdangthue;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;
    
    @ManyToOne
	@JoinColumn(name = "room_type_id")
	RoomTypes RoomTypes;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels Hotels;

	@JsonIgnore
	@OneToMany(mappedBy = "Rooms")
	List<ServiceRooms> Service_Room;

	@JsonIgnore
	@OneToMany(mappedBy = "Rooms")
	List<Images_Room> Images_Room;

	@JsonIgnore
	@OneToMany(mappedBy = "Rooms")
	List<Feedback> Feedback;

	@JsonIgnore
	@OneToMany(mappedBy = "Rooms")
	List<Booking_Room> Booking_Room;
}
