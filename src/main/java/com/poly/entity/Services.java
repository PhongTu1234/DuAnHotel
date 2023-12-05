package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity

@Table(name = "Services")
public class Services implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id" )
	Integer id;

	@Column(name = "service_name")
	@NotBlank(message = "Không được bỏ trống")
	String name;

//    @ManyToOne
//	@JoinColumn(name = "image_id")
//	Images Images;

	@JsonIgnore
	@OneToMany(mappedBy = "Services")
	List<ServiceRooms> Service_Rooms;
}