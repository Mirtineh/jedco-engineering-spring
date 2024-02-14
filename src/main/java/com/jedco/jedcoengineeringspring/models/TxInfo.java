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
@Table(name = "tx_info")
public class TxInfo extends BaseEntity{
    @Column(name="Feeder_code", length=100)
    private String feederCode;

    @Column(name="Trafo_capacity_in_KVA", precision=53, scale=0)
    private Double trafoCapacityInKva;

    @Column(name="Trafo_code", length=100)
    private String trafoCode;

    @Column(name="MV_pole_code_near_to_trafo", length=100)
    private String mvPoleCodeNearToTrafo;

    @Column(name="Easting", precision=53, scale=0)
    private Double easting;

    @Column(name="Northing", precision=53, scale=0)
    private Double northing;

    @Column(name="Latitude", precision=53, scale=0)
    private Double latitude;

    @Column(name="Longitude", precision=53, scale=0)
    private Double longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registered_on", nullable=false, length=23)
    private Date registeredOn;

    @Column(name="registered_by", precision=53, scale=0)
    private Long registeredBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name="updated_by", precision=53, scale=0)
    private Long updatedBy;

    @Column(name = "box_sequence", nullable = false)
    private Long boxSequence;
}
