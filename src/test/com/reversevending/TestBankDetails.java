package com.reversevending;

import com.reversevending.databaseOperationsDAO.BankDetailsDAO;
import com.reversevending.domain.Address;
import com.reversevending.domain.BankDetails;
import com.reversevending.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestBankDetails {

    @Test
    public void crud() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //create(session);

        BankDetailsDAO dao = new BankDetailsDAO();
        BankDetails bank = dao.getBankDetailsByCustomerId(107);
        System.out.println(bank);
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

        BankDetails bank = new BankDetails();
        bank.setCustomer(customer);
        bank.setAccountNumber(97438);
        bank.setBranchCode(984);
        bank.setName("Absa");

        BankDetails bank2 = new BankDetails();
        bank2.setCustomer(customer);
        bank2.setAccountNumber(97438);
        bank2.setBranchCode(984);
        bank2.setName("Absa");



        session.beginTransaction();
        session.save(customer);
        session.save(bank);
        session.save(bank2);
        session.getTransaction().commit();
    }
}
