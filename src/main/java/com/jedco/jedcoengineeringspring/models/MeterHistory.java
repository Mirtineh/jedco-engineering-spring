package com.jedco.jedcoengineeringspring.models;

import com.jedco.jedcoengineeringspring.config.LineEnum;
import com.jedco.jedcoengineeringspring.config.MeterHistoryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meter_history")
public class MeterHistory extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MeterHistoryType meterHistoryType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", length=23)
    private Date registeredOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter")
    private MeterData meter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "old_meter")
    private MeterData oldMeter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "old_pole_data")
    private PoleData oldPole;


}
