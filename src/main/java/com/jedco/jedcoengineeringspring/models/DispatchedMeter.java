package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dispatched_meter_customer")
public class DispatchedMeter{
    @Id
    @Column(name="meter_number", length=100)
    private String meterNo;

    @Column(name="customer_name", length=100)
    private String name;
}
