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
@Table(name = "img_room")
public class Images_Room implements Serializable {

	@Id
	@Column(name = "img_room_id")
	Integer id;
	
	@Column(name = "img_name")
	String name;

	@ManyToOne
	@JoinColumn(name = "room_id")
	Rooms Rooms;
}