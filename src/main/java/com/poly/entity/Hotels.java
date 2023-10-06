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

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Hotels")
public class Hotels implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_name", nullable = false, length = 100)
    private String hotelName;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "Email_hotel", length = 50)
    private String email;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "amenities", columnDefinition = "TEXT")
    private String amenities;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "place_id")
    private Integer placeId;

//    @Column(name = "hotel_type_id")
//    private Integer hotelTypeId;

    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;
    
    @ManyToOne
	@JoinColumn(name = "hotel_type_id")
	HotelTypes ht_id;
}
