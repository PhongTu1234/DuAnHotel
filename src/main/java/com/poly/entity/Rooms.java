package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Rooms")
public class Rooms implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	Integer id;

	@Column(name = "roomname")
    String name;
	
	@Column(name = "rating")
    String rate;
    
    @Column(name = "price", precision = 10, scale = 2) 
    BigDecimal price;

    @Column(name = "soluongphong")
    Integer soluongphong;
    
    @Column(name = "soluongchocheckin")
    Integer soluongcheckin;
    
    @Column(name = "soluongtrong")
    Integer soluongtrong;
    
    @Column(name = "soluongdangthue")
    Integer soluongdangthue;

    @Column(name = "description")
    String description;
    
    @Transient
    int totalBookings;
    
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
	
	public Images_Room getFirstImage() {
        if (Images_Room != null && !Images_Room.isEmpty()) {
            return Images_Room.get(0);
        }
        return null;
    }
}
