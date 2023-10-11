package com.poly.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "ROOMTYPES")
public class RoomTypes implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    Integer room_type_id;

    @Column(name = "room_type_name")
    String room_type_name;

    @Column(name = "price", precision = 10, scale = 2) 
    BigDecimal price;

    @Column(name = "created_at")
    Long createdAt;
    
    @Column(name = "updated_at")
    Long updatedAt;
    
    @JsonIgnore
	@OneToMany(mappedBy = "rt_id")
	List<Rooms> roomtypes;
    
}