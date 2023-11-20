package com.example.Web.System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="user")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    @Id
    @Column(name="user_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name="user_name",length = 255)
    private String userName;

    @Column(name="email",length = 255)
    private String email;

    @Column(name = "password",length = 255)
    private String password;

    @Column(name = "role", length = 255)
    private String role;

    public String getRole() {
        return role;
    }
}
