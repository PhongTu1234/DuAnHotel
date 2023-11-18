package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "img_hotel")
public class Images_Room implements Serializable {

	@Id
	@Column(name = "hotel_img_id")
	Integer id;

	@ManyToOne
	@JoinColumn(name = "room_id")
	Rooms Rooms;

	@ManyToOne
	@JoinColumn(name = "image_id")
	Images Images;
}