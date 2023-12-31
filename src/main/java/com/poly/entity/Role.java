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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Roles")
public class Role implements Serializable {

	@Id
	@Column(name = "role_id")
	String id;

	@Column(name = "Name")
	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "Role")
	List<Authority> Authority;
}