package com.example.Web.System.entity;

import com.example.Web.System.entity.enums.LocationEnum;
import com.example.Web.System.entity.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "issue")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issueID;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "issue_title_id")
    private IssueTitle issueTitle;

    @Enumerated(EnumType.STRING)
    private LocationEnum location;

    private String date;
    private String startTime;
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "informed_by_user_id")
    private User informedByUser;

    @ManyToOne
    @JoinColumn(name = "action_taken_by_user_id")
    private User actionTakenByUser;

    @Column(name = "duration")
    private Time duration;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @PrePersist
    @PreUpdate
    private void calculateDuration() {
        if (date != null && startTime != null && endTime != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date start = dateFormat.parse(date + " " + startTime);
                Date end = dateFormat.parse(date + " " + endTime);

                long durationMillis = end.getTime() - start.getTime();

                duration = new Time(durationMillis);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
