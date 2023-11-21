package com.example.Web.System.dto;

import com.example.Web.System.entity.enums.LocationEnum;
import com.example.Web.System.entity.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

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
    private User informedBy;
    private User actionTakenBy;
    private Time duration;
    private StatusEnum status;

}
