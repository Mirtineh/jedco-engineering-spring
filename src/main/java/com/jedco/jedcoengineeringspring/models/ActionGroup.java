package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jd_action_group")
public class ActionGroup extends BaseEntity {
    @Column(name="group_name", length=250)
    private String groupName;

    @Column(name="group_description", length=250)
    private String groupDescription;
}
