package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Images")
public class Images implements Serializable {

    @Id
    @Column(name = "image_id")
    Integer imageid;

    @Column(name = "img_name")
    String imgName;
    
    @ManyToOne
	@JoinColumn(name = "img_id")
	Blogs img_id;
    
    @JsonIgnore
	@OneToMany(mappedBy = "imtId")
	List<Images_Hotel> ImgHotelId;
}