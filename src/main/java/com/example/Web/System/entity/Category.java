package com.example.Web.System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryID;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<IssueTitle> issueTitles;

    public void deactivate() {
        this.activeState = false;
    }
}

