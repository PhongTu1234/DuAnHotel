package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "HOTELTYPES")
public class HotelTypes implements Serializable{
	@Id
    @Column(name = "hotel_type_id")
    Integer hotel_type_id;

    @Column(name = "hotel_level")
    Float hotelLevel;

    @JsonIgnore
	@OneToMany(mappedBy = "ht_id")
	List<Hotels> hoteltypes;
    
}