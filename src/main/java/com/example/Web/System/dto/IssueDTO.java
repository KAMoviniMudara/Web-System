package com.example.Web.System.dto;

import com.example.Web.System.entity.enums.LocationEnum;
import com.example.Web.System.entity.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueDTO {
    @Min(value = 1, message = "Issue ID must be a positive integer")
    private int issueID;

    @Min(value = 1, message = "Category ID must be a positive integer")
    private int categoryID;

    @Min(value = 1, message = "Issue Title ID must be a positive integer")
    private int issueTitleID;

    @NotNull(message = "Location cannot be null")
    private LocationEnum location;

    @NotEmpty(message = "Start date cannot be empty")
    private String startDate;

    @NotEmpty(message = "End date cannot be empty")
    private String endDate;

    @NotEmpty(message = "Start time cannot be empty")
    private String startTime;

    @NotEmpty(message = "End time cannot be empty")
    private String endTime;

    @Min(value = 1, message = "Informed By User ID must be a positive integer")
    private int informedByUserID;

    @Min(value = 1, message = "Action Taken By User ID must be a positive integer")
    private int actionTakenByUserID;

    private Long durationMillis;

    @NotNull(message = "Status cannot be null")
    private StatusEnum status;
}

