package com.example.Web.System.dto.request;

import com.example.Web.System.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCategorySaveDTO {

    private Long categoryID;
    private String categoryName;
    private boolean activeState;



}
