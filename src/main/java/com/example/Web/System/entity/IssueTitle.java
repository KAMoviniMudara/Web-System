package com.example.Web.System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "issue_title")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_title_id")
    private Long issueTitleID;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;


}
