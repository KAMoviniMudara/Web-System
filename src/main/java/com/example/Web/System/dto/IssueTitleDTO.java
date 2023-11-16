package com.example.Web.System.dto;

import com.example.Web.System.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueTitleDTO {
    private int issueID;
    private String title;
    private CategoryDTO categoryID;
    private boolean activeState;



}
