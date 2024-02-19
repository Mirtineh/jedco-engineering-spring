package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actionGroup")
    private List<UserAction> userActions = new ArrayList<>();

}
