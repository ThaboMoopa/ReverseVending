package com.reversevending.beans;

import com.reversevending.databaseOperationsDAO.CustomerDAO;
import com.reversevending.databaseOperationsDAO.TransactionLineDAO;
import com.reversevending.domain.Customer;
import com.reversevending.domain.TransactionLine;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class HomepageBean implements Serializable {

    private static CustomerDAO customerDAO = new CustomerDAO();
    private static Customer customer = new Customer();
    private static TransactionLineDAO transactionLineDAO = new TransactionLineDAO();

    public boolean userLoggedIn(long customer_id)
    {
        customer = customerDAO.getById(customer_id);
        if(customer.getName().equals("admin") && customer.getEmail().equals("admin@admin.co.za"))
            return false;
        else
            return true;


    }

    public boolean checkOutButtonDisable(long transaction_id)
    {
        double total = transactionLineDAO.totalForEachTransaction(transaction_id);
        if(total == 0.00)
        {
            return true;
        }
        else
            return false;
    }
    public String editCustomer()
    {
        System.out.println("******* In function******");
        return "adminEditCustomer.xhtml";
//        try{
//            System.out.println("(((((((((((((" + customerID);
//            redirect();
//            id = customerID;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return redirect();
    }


}
