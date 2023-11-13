package com.example.Web.System.entity;

import com.example.Web.System.entity.enums.LocationEnum;
import com.example.Web.System.entity.enums.ReportCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ReportID;

    @Enumerated(EnumType.STRING)
    private LocationEnum location;

    private String FromDate;
    private String ToDate;

    @Enumerated(EnumType.STRING)
    private ReportCategoryEnum ReportCategory;

    private String ReportDescription;
}
