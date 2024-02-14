package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jd_update_history")
public class UpdateHistory extends BaseEntity{
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="updated_by", nullable=false)
    private User user;

    @Column(name="old_pole_data")
    private String oldPoleData;

    @Column(name="old_meter_data")
    private String oldMeterData;

    @Column(name="new_update_data")
    private String newUpdateData;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=23)
    private Date updatedOn;
}
