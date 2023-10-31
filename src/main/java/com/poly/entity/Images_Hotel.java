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
@Table(name = "img_hotel", uniqueConstraints = { @UniqueConstraint(columnNames = { "hotel_id", "image_id" }) })
public class Images_Hotel implements Serializable {

	@Id
	@Column(name = "hotel_img_id")
	Integer id;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	Hotels Hotels;
	
	@ManyToOne
	@JoinColumn(name = "image_id")
	Images Images;
}