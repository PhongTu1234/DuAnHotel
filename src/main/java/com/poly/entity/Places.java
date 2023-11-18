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
@Table(name = "Place")
public class Places implements Serializable {

	@Id
	@Column(name = "place_id")
	Integer id;

	@Column(name = "place_name")
	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "Place")
	List<Hotels> Hotel;
}