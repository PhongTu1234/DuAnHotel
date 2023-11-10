package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "ROOMTYPES")
public class RoomTypes implements Serializable {
	@Id
	@Column(name = "room_type_id")
	Integer id;

	@Column(name = "room_type_name")
	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "RoomTypes")
	List<Rooms> Rooms;

}