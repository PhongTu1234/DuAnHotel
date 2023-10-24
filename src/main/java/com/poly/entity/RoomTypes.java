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
    @Column(name = "room_type_id")
    Integer room_type_id;

    @Column(name = "room_type_name")
    String room_type_name;

    @JsonIgnore
	@OneToMany(mappedBy = "rt_id")
	List<Rooms> roomtypes;
    
}