package com.reversevending;

import com.reversevending.beans.UserRolesBean;
import com.reversevending.databaseOperationsDAO.UserRolesDAO;
import com.reversevending.domain.Customer;
import com.reversevending.domain.Roles;
import com.reversevending.domain.Transactions;
import com.reversevending.domain.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

public class TestUserRoles {

    @Test
    public void add()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        create(session);



    }

    public void create(Session session)
    {
        System.out.println("Creating car records...");
        Customer customer = new Customer();
        customer.setName("Thembi");
        customer.setSurname("Thembi");
        customer.setContact("0849262266");
        customer.setEmail("thembi.moopa@gmail.com");
        customer.setPassword("mysql");


        Transactions transaction = new Transactions();
        transaction.setCustomer(customer);// .setStreet("M");
        transaction.setTransactionDate(LocalDate.now());

        Roles roles = new Roles();
        roles.setRoleName("Admin");

        UserRoles userRoles = new UserRoles();
        userRoles.setCustomer(customer);
        userRoles.setRole(roles);

        session.beginTransaction();
        session.save(customer);
        session.save(transaction);
        session.save(roles);
        //session.save(userRoles);
        session.getTransaction().commit();

        long customerId = customer.getId();
        long roleId = roles.getId();

        System.out.println(customerId);
        System.out.println(roleId);


        //session.beginTransaction();
       UserRolesDAO userRolesDAO = new UserRolesDAO();
       userRolesDAO.add(userRoles, customerId, roleId);
        //session.getTransaction().commit();

        //UserRolesBean bean = new UserRolesBean();
        //bean.add();
    }
}
