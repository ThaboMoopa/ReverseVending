package com.reversevending;

import com.reversevending.beans.RolesBean;
import com.reversevending.databaseOperationsDAO.RolesDAO;
import com.reversevending.domain.Address;
import com.reversevending.domain.Customer;
import com.reversevending.domain.Roles;
import com.reversevending.domain.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestRoles {

    @Test
    public void Add()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //create(session);

       // RolesBean bean = new RolesBean();
       // bean.preloadData();

        RolesDAO roles = new RolesDAO();
        roles.preLoad();
        System.out.println(roles.getAll().isEmpty());

        //roles.preLoad();
        //session.close();
    }

    private void create(Session session) {
        Customer customer = new Customer();
        customer.setName("Thembi");
        customer.setSurname("Thembi");
        customer.setContact("0849262266");
        customer.setEmail("thembi.moopa@gmail.com");
        customer.setPassword("mysql");

        Roles role = new Roles();
        role.setRoleName("Admin");

        UserRoles user = new UserRoles();
        user.setCustomer(customer);
        user.setRole(role);

        session.beginTransaction();
        session.save(customer);
        session.save(role);
        session.save(user);
        session.getTransaction().commit();
    }
}
