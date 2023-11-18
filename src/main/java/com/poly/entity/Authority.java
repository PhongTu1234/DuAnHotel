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
@Table(name = "Authorities", uniqueConstraints = { @UniqueConstraint(columnNames = { "cmt", "role_id" }) })
public class Authority implements Serializable {

	@Id
	@Column(name = "Id")
	Integer id;

	@ManyToOne
	@JoinColumn(name = "cmt")
	Users Users;

	@ManyToOne
	@JoinColumn(name = "role_id")
	Role Role;
}