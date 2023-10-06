package com.poly.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "cmt", nullable = false, unique = true, length = 20)
    private String cmt;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "access_level")
    private boolean accessLevel;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name = "updated_at")
    private long updatedAt;

    // Getters and setters
}
