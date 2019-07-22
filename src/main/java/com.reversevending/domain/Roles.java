package com.reversevending.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Roles", schema = "reversevendingmachine")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId")
    private long id;

    @OneToMany
    private Set<UserRoles> roles;

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Roles(){}
    public Roles(String roleName)
    {
        this.roleName = roleName;
    }

}
