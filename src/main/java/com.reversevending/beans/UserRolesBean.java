package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.UserRolesDAO;
import com.reversevending.domain.Customer;
import com.reversevending.domain.Roles;
import com.reversevending.domain.UserRoles;
import com.reversevending.repository.BeansInterface;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;

@Named
@SessionScoped
public class UserRolesBean implements Serializable, BeansInterface {

    private long id;

    private Customer customer;

    private Roles role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    private UserRolesDAO userRolesDAO;

    @Override
    public void add(long customerId, long rolesId) {
        userRolesDAO = new UserRolesDAO();
        userRolesDAO.add(new UserRoles(), customerId, rolesId);
    }
}
