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
@Table(name = "jd_user_role")
public class UserRole extends BaseEntity{

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="role_status", nullable=false)
    private Status status;

    @Column(name="role_name", nullable=false, length=100)
    private String roleName;

    @Column(name="role_description", length=250)
    private String roleDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registered_date", nullable=false, length=23)
    private Date registeredDate;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="userRole")
    private Set<User> users = new HashSet<>(0);

    @OneToMany(fetch=FetchType.EAGER, mappedBy="userRole")
    private Set<RoleDefinition> roleDefinitions = new HashSet<>(0);
}
