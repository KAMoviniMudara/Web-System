package com.example.Web.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportDTO {

    private Long reportId;

    private String reportName;
    private Date startDate;
    private Date endDate;
    private int categoryID;
}