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
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Users")

public class Users implements Serializable{
    @Id
    @Pattern(regexp = "^0[1-9][0-9]{10}$", message = "CMT phai du 12 so")
    @Column(name = "cmt")
    String cmt;

	@Column(name = "username")
	@NotBlank(message = "Không được bỏ trống")
	String username;
	
    @Column(name = "email", length = 100)
    @Pattern(regexp = "^[0-9a-zA-Z]([-.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+[a-zA-Z]{2,9}$", message = "Email không đúng định dạng")
    String email;

    @Column(name = "password")
    @NotBlank(message = "không được bỏ trống")
    String password;

    @Column(name = "phone_number", length = 20)
    @Pattern(regexp = "^0[3-9][0-9]{8}$", message = "SĐT phai du 10 so")
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
