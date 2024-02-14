package com.jedco.jedcoengineeringspring.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jd_role_definition")
public class RoleDefinition extends BaseEntity{

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="defn_status", nullable=false)
    private Status status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_action", nullable=false)
    private UserAction userAction;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_role", nullable=false)
    private UserRole userRole;
}
