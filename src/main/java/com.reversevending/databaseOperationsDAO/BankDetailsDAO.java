package com.reversevending.databaseOperationsDAO;

import com.reversevending.domain.BankDetails;
import com.reversevending.domain.Customer;
import com.reversevending.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class BankDetailsDAO implements Serializable {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private CustomerDAO customerDAO = new CustomerDAO();

    //AddCustomer to the DB
    public void addBankDetails(BankDetails bankDetails)
    {
        //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderValuesMap().get("city"));
        try{

            transaction = session.beginTransaction();
            Customer dbCustomer = (Customer) session.get(Customer.class, customerDAO.getCustomerID());
            bankDetails.setCustomer(dbCustomer);
            session.save(bankDetails);
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
    public void deleteBankDetails(long id){
        try{
            transaction = session.beginTransaction();
            BankDetails bankDetailsId = (BankDetails) session.load(BankDetails.class, new Long(id));
            session.delete(bankDetailsId);

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
    public List<BankDetails> getBankDetailsById(long id){

        BankDetails particularBankDetails = new BankDetails();

        List<BankDetails> particularBankDetailsList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM BankDetails WHERE id= :BankDetails_id").setParameter("BankDetails_id", id);
            particularBankDetails = (BankDetails) query.uniqueResult();
            particularBankDetailsList = query.list();
            System.out.println("Student with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findBankDetailsById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularBankDetailsList;
    }

    //Method to update the Customer
    public void updateBankDetails(BankDetails BankDetails)
    {
        try{
            transaction = session.beginTransaction();
            session.update(BankDetails);
            System.out.println("Customer with id= " + BankDetails.getId() + " has been successfully updated.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }
    public BankDetails getBankDetailsByCustomerId(long customerId)
    {
        BankDetails uniqueBankDetails = new BankDetails();
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM BankDetails WHERE customer.id = :customerId").setParameter("customerId", customerId);
            uniqueBankDetails = (BankDetails) query.uniqueResult();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }

        return uniqueBankDetails;
    }

}
