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
