package com.example.Web.System.dto;

import com.example.Web.System.entity.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResolvedIssueDTO {
    @NotNull
    private int issueID;

    @NotNull
    private int categoryID;

    @NotNull
    private int issueTitleID;

    @NotEmpty
    private String startDate;

    @NotEmpty
    private String endDate;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    @NotNull
    private int actionTakenByUserID;

    @NotNull
    private Long durationMillis;

    @NotNull
    private StatusEnum status;
}
