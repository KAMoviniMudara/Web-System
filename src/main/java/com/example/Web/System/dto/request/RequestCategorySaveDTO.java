package com.example.Web.System.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCategorySaveDTO {
    private String categoryName;
    private boolean activeState;
}
