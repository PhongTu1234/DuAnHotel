package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "id")
    Integer id;

    @Column(name = "title")
    String Title;
    
    @Column(name = "content")
    String content;
    
    @Column(name = "short_description")
    String ShoDes;
    
    @Column(name = "author")
    String Author;
    
    @ManyToOne
	@JoinColumn(name = "image_id")
	Images Images;
}