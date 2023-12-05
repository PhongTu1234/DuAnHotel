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
@Table(name = "Blogs")
public class Blogs implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;

	@Column(name = "title")
	String Title;

	@Column(name = "description")
	String Description;

	@Column(name = "author")
	String Author;
	
	@Column(name = "image_name")
	String img;

//	@ManyToOne
//	@JoinColumn(name = "image_id")
//	Images Images;
}