package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "img_hotel")
public class Images_Hotel implements Serializable {

	@Id
	@Column(name = "img_hotel_id")
	Integer id;
	
	@Column(name = "img_name")
	String name;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels Hotels;

}