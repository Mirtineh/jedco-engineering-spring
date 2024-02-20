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
@Table(name = "jd_meter_data")
public class MeterData extends BaseEntity {

    @Column(name="customer_name", length=100)
    private String customerName;

    @Column(name="meter_no", length=100)
    private String meterNo;

    @Column(name="com_cable_length", length=100)
    private String comCableLength;

    @Column(name="service_cable_length", length=100)
    private String serviceCableLength;

    @Column(name="estimated_load", length=100)
    private String estimatedLoad;

    @Column(name="customer_type", length=100)
    private String customerType;

    @Column(name="meter_type", length=100)
    private String meterType;

    @Column(name="connected_phase", length=100)
    private String connectedPhase;

    @Column(name="service_cable_type", length=100)
    private String serviceCableType;

    @Column(name="meter_anomaly", length=100)
    private String meterAnomaly;

    @Column(name="meter_reg_type", length=100)
    private String meterRegType;

    @Column(name="box_no", length=100)
    private String boxNo;

    @Column(name="box_assembly_type", length=100)
    private String boxAssemblyType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registered_on", length=23)
    private Date registeredOn;

    @Column(name="registered_by", length=100)
    private Long registeredBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_no_id", nullable = false)
    private BoxNumber boxNumber;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="pole_id", nullable=false)
    private PoleData poleData;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="meter_status", nullable=false)
    private Status status;
}
