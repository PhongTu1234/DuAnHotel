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
    Integer id;

    @Column(name = "hotel_level")
    Float Level;

    @JsonIgnore
	@OneToMany(mappedBy = "HotelTypes")
	List<Hotels> Hotels;
    
}