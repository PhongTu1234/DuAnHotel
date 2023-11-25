package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Users")

public class Users implements Serializable{
    @Id
    @Column(name = "cmt")
    @NotBlank(message = "cmt")
    String cmt;

	@Column(name = "username")
	String username;
	
    @Column(name = "email", length = 100)
    @NotBlank(message = "email")
    String email;

    @Column(name = "password")
    @NotBlank(message = "password")
    String password;

    @Column(name = "phone_number", length = 20)
    @NotBlank(message = "phone_number")
    String phoneNumber;
    
    @Column(name = "Token")
    String token;
    
    @Column(name = "Changedpass")
    Boolean Changedpass;

//    public Boolean isChangedpass() {
//        return Changedpass;
//    }
//    
//    public void setChangedpass(Boolean changedpass) {
//        this.Changedpass = changedpass;
//    }
    
    @JsonIgnore
	@OneToMany(mappedBy = "Users",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Authority> authorities;
    
    @JsonIgnore
	@OneToMany(mappedBy = "Users")
	List<Bookings> Bookings;

	@JsonIgnore
	@OneToMany(mappedBy = "Users")
	List<Feedback> Feedback;
}
