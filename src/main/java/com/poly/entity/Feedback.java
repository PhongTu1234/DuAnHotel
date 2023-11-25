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
@Table(name = "Feedback")
public class Feedback implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feed_back_id")
	Integer id;

	@Column(name = "description")
	String Des;

	@ManyToOne
	@JoinColumn(name = "room_id")
	Rooms Rooms;

	@ManyToOne
	@JoinColumn(name = "cmt")
	Users Users;

}
