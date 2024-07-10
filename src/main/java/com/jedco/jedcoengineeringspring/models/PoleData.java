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
@Table(name = "jd_pole_data")
public class PoleData extends BaseEntity{
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="pole_status", nullable=false)
    private Status status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="reg_by", nullable=false)
    private User registeredBy;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tx_id", nullable=false)
    private TxInfo transformer;

    @Column(name="feeder", length=10)
    private String feeder;

    @Column(name="tx_no", length=100)
    private String txNo;

    @Column(name="branch_code", length=100)
    private String branchCode;

    @Column(name="pole_no", length=100)
    private String poleNo;

    @Column(name="assembly_type", length=100)
    private String assemblyType;

    @Column(name="conductor_type", length=100)
    private String conductorType;

    @Column(name="pole_type", length=100)
    private String poleType;

    @Column(name="latitude", length=100)
    private String latitude;

    @Column(name="longitude", length=100)
    private String longitude;

    @Column(name="pole_feature", length=100)
    private String poleFeature;

    @Column(name="location_accuracy", length=100)
    private String locationAccuracy;

    @Column(name="remark", length=2000)
    private String remark;

    @Column(name="pole_anomaly", length=2000)
    private String pole_anomaly;

    @Column(name="northing", length=2000)
    private String northing;

    @Column(name="easting", length=2000)
    private String easting;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="submitted_on", nullable=false, length=23)
    private Date submittedOn;

    @Column(name="pole_reg_type", length=100)
    private String poleRegType;

    @Column(name="last_updated_by", length=100)
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_updated_on", nullable=false, length=23)
    private Date updatedOn;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="poleData")
    private Set<MeterData> meterDataSet = new HashSet<MeterData>(0);

    @OneToMany(fetch=FetchType.EAGER, mappedBy="poleData")
    private Set<BoxNumber> boxNumbers= new HashSet<>();
}
