package com.jedco.jedcoengineeringspring.models;

import com.jedco.jedcoengineeringspring.config.LineEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tx_line_reading")
public class TxLineReading extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "line")
    private LineEnum line;

    @Column(name="current_value", precision=53, scale=0)
    private Double current;

    @Column(name="voltage_value", precision=53, scale=0)
    private Double voltage;

    @Column(name="power", precision=53, scale=0)
    private Double power;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tx_reading_id", nullable=false)
    private TxReading txReading;
}
