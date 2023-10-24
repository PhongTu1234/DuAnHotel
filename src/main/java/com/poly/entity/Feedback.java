package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Feedback")
public class Feedback implements Serializable {

    @Id
    @Column(name = "feedBack_id")
    Integer BRId;

    @Column(name = "description")
	String Des;
    
    @ManyToOne
	@OneToMany(mappedBy = "room_id", fetch = FetchType.EAGER)
	Rooms r_id;
    
    @ManyToOne
	@OneToMany(mappedBy = "phone_number", fetch = FetchType.EAGER)
	Users PhoneNumber;
    
    
}
