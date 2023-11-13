package com.example.Web.System.entity;

import javafx.css.Styleable;

import javax.persistence.*;

@Entity
public class IssueTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issueID;
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;



}
