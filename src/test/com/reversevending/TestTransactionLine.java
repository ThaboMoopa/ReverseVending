package com.reversevending;

import com.reversevending.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

public class TestTransactionLine {
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


        Transactions trns = new Transactions();
        trns.setCustomer(customer);// .setStreet("M");
        trns.setTransactionDate(LocalDate.now());

        Products pr = new Products();
        pr.setDescription("Cans");
        pr.setPrice(885);
        pr.setName("CAns");


        TransactionLine transa = new TransactionLine();
        transa.setTotal(800);
        transa.setQuantity(5);
        transa.setTransaction(trns);
        //transa.setTransaction(trns);
        transa.setProducts(pr);

        TransactionLine transa2 = new TransactionLine();
        transa2.setTotal(800);
        transa2.setQuantity(5);
        transa2.setTransaction(trns);
        transa2.setProducts(pr);



        session.beginTransaction();
        session.save(customer);
        session.save(trns);
        session.save(pr);
        session.save(transa);
        session.save(transa2);
        session.getTransaction().commit();
    }
}
