package com.example.Web.System.dto;

import com.example.Web.System.entity.enums.ActionTakenByEnum;
import com.example.Web.System.entity.enums.InformedByEnum;
import com.example.Web.System.entity.enums.LocationEnum;
import com.example.Web.System.entity.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
    private int issueID;
    private int categoryID;
    private int issueTitleID;
    private LocationEnum location;
    private String date;
    private String startTime;
    private String endTime;
    private InformedByEnum informedBy;
    private ActionTakenByEnum actionTakenBy;
    private Time duration;
    private StatusEnum status;

}
