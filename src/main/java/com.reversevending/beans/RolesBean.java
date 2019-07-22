package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.RolesDAO;
import com.reversevending.domain.UserRoles;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Set;

@Named
@SessionScoped
public class RolesBean implements Serializable {

    private long id;

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

    private RolesDAO rolesDAO;

    public void preloadData()
    {
        rolesDAO  = new RolesDAO();
        rolesDAO.preLoad();

    }

}
