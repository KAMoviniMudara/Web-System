package com.example.Web.System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @Column(name = "category_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;



}
