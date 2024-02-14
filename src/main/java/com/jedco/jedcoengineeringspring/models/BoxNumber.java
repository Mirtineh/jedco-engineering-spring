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
@Table(name = "meter_box_no")
public class BoxNumber extends BaseEntity{

    @Column(name="box_number", nullable = false)
    private Long boxNumber;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="pole_no_id", nullable=false)
    private PoleData jdPoleData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", nullable=false, length=23)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", nullable=false, length=23)
    private Date updatedOn;

    @Transient
    public String getFormattedBoxNumber() {
        if (boxNumber >= 0 && boxNumber <= 9) {
            return "B0" + boxNumber;
        }
        return "B"+boxNumber.toString();
    }
}
