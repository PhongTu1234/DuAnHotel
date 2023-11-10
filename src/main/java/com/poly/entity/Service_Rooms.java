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
@Table(name = "ServiceRooms", uniqueConstraints = { @UniqueConstraint(columnNames = { "room_id", "service_id" }) })
public class Service_Rooms implements Serializable {

	@Id
	@Column(name = "ServiceRooms_Id")
	Integer id;

	@ManyToOne
	@JoinColumn(name = "room_id")
	Rooms Rooms;

	@ManyToOne
	@JoinColumn(name = "service_id")
	Services Services;
}