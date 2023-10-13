package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Rooms", uniqueConstraints = { @UniqueConstraint(columnNames = { "hotel_id" }) })
public class Rooms implements Serializable {

	@Id
    @Column(name = "room_id")
    Integer roomId;

//    @Column(name = "hotel_id")
//    Integer hotelId;

//    @Column(name = "room_type_id")
//    Integer roomTypeId;

    @Column(name = "room_number", length = 10)
    String roomNumber;

    @Column(name = "status", length = 50)
    String status;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "image_id")
    Integer imageId;

    @Column(name = "service_id", length = 50)
    String serviceId;

    @Column(name = "created_at")
    Long createdAt;

    @Column(name = "updated_at")
    Long updatedAt;
    
    @ManyToOne
	@JoinColumn(name = "room_type_id")
	RoomTypes rt_id;
    
    @ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels hotel_id;
}
