package com.reversevending;

import com.reversevending.domain.Address;
import com.reversevending.domain.BankDetails;
import com.reversevending.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestCustomer {
    @Test
    public void crud() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        create(session);
        //read(session);

        //update(session);
        // read(session);

        //delete(session);
        //read(session);

        session.close();
    }

    private void create(Session session) {
        System.out.println("Creating car records...");
        Customer customer = new Customer();
        customer.setName("Thembi");
        customer.setSurname("Thembi");
        customer.setContact("0849262266");
        customer.setEmail("thembi.moopa@gmail.com");
        customer.setPassword("mysql");

        Address address = new Address();
        address.setStreet("M");
        address.setPostalCode(323);
        address.setHouseNumber(25);
        address.setDescription("Long");
        address.setCustomer(customer);

        Address address2 = new Address();
        address2.setStreet("L");
        address2.setPostalCode(837);
        address2.setHouseNumber(27);
        address2.setDescription("Main");
        address2.setCustomer(customer);


        session.beginTransaction();
        session.save(customer);
        session.save(address);
        session.save(address2);
        session.getTransaction().commit();
    }

}
