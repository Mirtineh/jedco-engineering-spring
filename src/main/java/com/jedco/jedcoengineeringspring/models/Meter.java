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
@Table(name = "meters")
public class Meter{
    @Id
    @Column(name="meterno")
    private String meterNo;
}
