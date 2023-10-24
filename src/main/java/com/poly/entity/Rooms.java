package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Rooms", uniqueConstraints = { @UniqueConstraint(columnNames = { "hotel_id" }) })
public class Rooms implements Serializable {

	@Id
    @Column(name = "room_id")
    Integer roomId;

    @Column(name = "room_number", length = 10)
    String roomNumber;
    
    @Column(name = "price", precision = 10, scale = 2) 
    BigDecimal price;

    @Column(name = "status", length = 50)
    String status;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "image_id")
    Integer imageId;

    @Column(name = "service_id", length = 50)
    String serviceId;

    @ManyToOne
	@JoinColumn(name = "room_type_id")
	RoomTypes rt_id;
    
    @ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels Hotel;
    
    @JsonIgnore
	@OneToMany(mappedBy = "RoomId")
	List<Service_Rooms> Service_Room;
}
