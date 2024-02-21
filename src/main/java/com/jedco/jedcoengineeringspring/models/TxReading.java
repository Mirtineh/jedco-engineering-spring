package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tx_reading")
public class TxReading extends BaseEntity{
    @Column(name="transformer_loading", precision=53, scale=0)
    private Double transformerLoading;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", nullable=false, length=23)
    private Date createdOn;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="created_by", nullable=false)
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=23)
    private Date updatedOn;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="updated_by")
    private User updatedBy;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="transformer_id", nullable=false)
    private TxInfo transformer;

    @Column(name="remark")
    private String remark;

    @Column(name="neutral_current", precision=53, scale=0)
    private Double neutralCurrent;

    @Column(name="branch")
    private String branch;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="txReading",cascade = CascadeType.ALL)
    private Set<TxLineReading> lineReadings= new HashSet<>();
    @PrePersist
    @PreUpdate
    private void computeTransformerLoading() {
        double totalLineReadingPower = 0.0;
        // Calculate the total power of all TxLineReading instances
        for (TxLineReading lineReading : lineReadings) {
            if (lineReading.getPower() != null) {
                totalLineReadingPower += lineReading.getPower();
            }
        }
        if (transformer != null && transformer.getTrafoCapacityInKva() != null && totalLineReadingPower > 0) {
            // Compute transformer loading using the formula
            transformerLoading = (totalLineReadingPower * 100) / (transformer.getTrafoCapacityInKva() * 0.95);
        }
    }
}
