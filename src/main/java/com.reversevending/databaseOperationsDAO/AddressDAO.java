package com.reversevending.databaseOperationsDAO;

import com.reversevending.domain.Address;
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

import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class AddressDAO implements Serializable {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private CustomerDAO customerDAO = new CustomerDAO();

    //AddCustomer to the DB
    public void addAddress(Address address)
    {
        //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderValuesMap().get("city"));
        try{

            transaction = session.beginTransaction();
            Customer dbCustomer = (Customer) session.get(Customer.class, customerDAO.getCustomerID());
            address.setCustomer(dbCustomer);
            session.save(address);

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
    public void deleteAddress(long id){
        try{
            transaction = session.beginTransaction();
            Address addressId = (Address) session.load(Address.class, new Long(id));
            session.delete(addressId);

            //XHTML response Text
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedId", id);
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
    public List<Address> getAddressById(long id){

        Address particularAddress = new Address();

        List<Address> particularAddressList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Address WHERE id= :address_id").setParameter("address_id", id);
            particularAddress = (Address) query.uniqueResult();
            particularAddressList = query.list();
            System.out.println("Student with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findAddressById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularAddressList;
    }

    //Method to update the Customer
    public void updateAddress(Address address)
    {
        try{
            transaction = session.beginTransaction();
            session.update(address);
            System.out.println("Customer with id= " + address.getId() + " has been successfully updated.");
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
