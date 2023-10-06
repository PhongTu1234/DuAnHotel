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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_type_id")
    private Integer hotel_type_id;

    @Column(name = "hotel_level")
    private Float hotelLevel;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;
    
    @JsonIgnore
	@OneToMany(mappedBy = "ht_id")
	List<Hotels> hoteltypes;
    
}