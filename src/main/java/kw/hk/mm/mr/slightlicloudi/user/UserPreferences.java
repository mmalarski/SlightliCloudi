package kw.hk.mm.mr.slightlicloudi.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
@Table
public class UserPreferences {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    private double latitude;
    private double longitude;
    private boolean isReceivingWeekly;
    private DayOfWeek weeklyReceivingWeekday;
    private LocalTime weeklyReceivingTime;
    private boolean isReceivingDaily;
    private LocalTime dailyReceivingTime;
    private boolean isReceivingWeekends;
    private DayOfWeek weekendsReceivingWeekday;
    private LocalTime weekendsReceivingTime;
    private boolean clothingRecommendation;
}
