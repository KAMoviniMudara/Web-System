package com.example.Web.System.dto.request;

import com.example.Web.System.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestIssueTitleSaveDTO {
    private String title;
    private Category category;
    private boolean activeState;

}
