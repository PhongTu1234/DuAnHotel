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
@Table(name = "Images")
public class Images implements Serializable {

	@Id
	@Column(name = "image_id")
	Integer image_id;

	@Column(name = "img_name")
	String name;
	
	@Column(name = "id")
	String id;
	
	@Column(name = "status")
	String status;

	@JsonIgnore
	@OneToMany(mappedBy = "Images")
	List<Blogs> Blogs;

	@JsonIgnore
	@OneToMany(mappedBy = "Images")
	List<Images_Hotel> Images_Hotel;

//	@JsonIgnore
//	@OneToMany(mappedBy = "Images")
//	List<Services> Service;
//
	@JsonIgnore
	@OneToMany(mappedBy = "Images")
	List<Images_Room> Images_Room;
}