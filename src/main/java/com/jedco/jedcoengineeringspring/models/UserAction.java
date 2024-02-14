package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jd_user_action")
public class UserAction extends BaseEntity{

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="action_group", nullable=false)
    private ActionGroup actionGroup;

    @Column(name="action_name", length=250)
    private String actionName;

    @Column(name="action_status", nullable=false)
    private long actionStatus;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="jdUserAction")
    private Set<RoleDefinition> jdRoleDefinitions = new HashSet<>(0);
}
