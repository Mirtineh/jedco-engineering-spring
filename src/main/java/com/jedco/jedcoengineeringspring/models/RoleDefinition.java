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
@Table(name = "jd_role_definition")
public class RoleDefinition extends BaseEntity{

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="defn_status", nullable=false)
    private Status status;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_action", nullable=false)
    private UserAction userAction;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_role", nullable=false)
    private UserRole userRole;

}
