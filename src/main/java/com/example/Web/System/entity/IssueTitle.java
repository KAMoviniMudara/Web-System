package com.example.Web.System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "IssueTitle")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class IssueTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issueTitleID;
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryID;
    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

}
