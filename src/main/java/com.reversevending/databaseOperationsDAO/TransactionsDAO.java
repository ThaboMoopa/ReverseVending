package com.reversevending.databaseOperationsDAO;

import com.reversevending.beans.TransactionsBean;
import com.reversevending.domain.Customer;
import com.reversevending.domain.Transactions;
import com.reversevending.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;

//import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class TransactionsDAO implements Serializable {
    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Long id;
    private CustomerDAO customerDAO;

    public Long getTransactionID()
    {
        System.out.println("*********************Transaction ID " + id);
        return id;
    }


    //AddCustomer to the DB
    public void addTransactions(long customerId)
    {
        //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderValuesMap().get("city"));
        try{
            transaction = session.beginTransaction();

            System.out.println("***************" + customerId);
            Transactions transactions = new Transactions();
            Customer dbCustomer = (Customer) session.get(Customer.class, customerId);
            System.out.println("**************ID*******" + dbCustomer.getId());
            transactions.setCustomer(dbCustomer);
            transactions.setTransactionDate(LocalDate.now());
            session.save(transactions);

            id = transactions.getId();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    //Delete customer from DB
    public void deleteTransaction(long id){
        try{
            transaction = session.beginTransaction();
            Transactions transactionId = (Transactions) session.load(Transactions.class, new Long(id));
            session.delete(transactionId);

            //XHTML response Text
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedId", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    //Read Customer details
    public Transactions getTransactionById(long id){

        Transactions particularTransaction = new Transactions();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Transactions WHERE id= :transactions_id").setParameter("transactions_id", id);
            //particularTransaction = (Transactions) query.uniqueResult();
            particularTransaction = (Transactions) query.getSingleResult();
            System.out.println("Student with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findAddressById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularTransaction;
    }

    //Method to update the Customer
    public void updateTransaction(Transactions transactions)
    {
        try{
            transaction = session.beginTransaction();
            session.update(transactions);
            System.out.println("Customer with id= " + transactions.getId() + " has been successfully updated.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }
}
