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
@Table(name = "jd_users")
public class User extends BaseEntity{

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_status", nullable=false)
    private Status status;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_role", nullable=false)
    private UserRole userRole;

    @Column(name="first_name", length=100)
    private String firstName;

    @Column(name="last_name", length=100)
    private String lastName;

    @Column(name="phone_no", nullable=false, length=100)
    private String phoneNo;

    @Column(name="email", length=100)
    private String email;

    @Column(name="username", length=100)
    private String username;

    @Column(name="password", length=100)
    private String password;

    @Column(name="registered_by")
    private Long registeredBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registered_date", nullable=false, length=23)
    private Date registeredDate;

    @Column(name="password_index")
    private Long passwordIndex;

    @Column(name="activation_no", nullable=false, length=10)
    private String activationNo;

    @Column(name="activation_index")
    private Long activationIndex;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="registeredBy")
    private Set<PoleData> poleDataSet = new HashSet<>(0);
}
