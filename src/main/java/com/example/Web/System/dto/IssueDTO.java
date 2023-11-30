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

    private long durationMillis;

    @NotNull(message = "Status cannot be null")
    private StatusEnum status;


    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getIssueTitleID() {
        return issueTitleID;
    }

    public void setIssueTitleID(int issueTitleID) {
        this.issueTitleID = issueTitleID;
    }

    public LocationEnum getLocation() {
        return location;
    }

    public void setLocation(LocationEnum location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getInformedByUserID() {
        return informedByUserID;
    }

    public void setInformedByUserID(int informedByUserID) {
        this.informedByUserID = informedByUserID;
    }

    public int getActionTakenByUserID() {
        return actionTakenByUserID;
    }

    public void setActionTakenByUserID(int actionTakenByUserID) {
        this.actionTakenByUserID = actionTakenByUserID;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(long durationMillis) {
        this.durationMillis = durationMillis;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void calculateDuration() {
        try {
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date start = dateFormat.parse(startDate + " " + startTime);
            java.util.Date end = dateFormat.parse(endDate + " " + endTime);

            long durationMillis = end.getTime() - start.getTime();
            setDurationMillis(durationMillis);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }
}
