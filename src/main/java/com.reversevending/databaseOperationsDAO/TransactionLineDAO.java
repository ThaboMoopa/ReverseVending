package com.reversevending.databaseOperationsDAO;

import com.reversevending.domain.Products;
import com.reversevending.domain.TransactionLine;
import com.reversevending.domain.Transactions;
import com.reversevending.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class TransactionLineDAO implements Serializable {
    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private ProductsDAO productsDAO;
    private static long id;
    private static TransactionsDAO transactionDAO = new TransactionsDAO();

    //AddCustomer to the DB
    public void addTransactionLine(TransactionLine transactionLine, long productId, long transactionId)
    {
        try{

            transaction = session.beginTransaction();
            Products dbProducts = (Products) session.get(Products.class, productId);
            Transactions dbTransactions  = (Transactions) session.get(Transactions.class, transactionId);
            transactionLine.setTransaction(dbTransactions);
            transactionLine.setProducts(dbProducts);
            transactionLine.setQuantity(transactionLine.getQuantity() + 1);
            transactionLine.setTotal(dbProducts.getPrice() * transactionLine.getQuantity());
            session.save(transactionLine);
            id = transactionLine.getId();

            //buttonPressed(productId);
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
    public void deleteTransactionLine(long id){

        try{
            transaction = session.beginTransaction();
            TransactionLine transactionLineId = (TransactionLine) session.load(TransactionLine.class, new Long(id));
            session.delete(transactionLineId);

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
    public TransactionLine getTransactionLineById(long id){

        TransactionLine particularTransactionLine = new TransactionLine();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine WHERE id= :transactionLine_id").setParameter("transactionLine_id", id);
            particularTransactionLine = (TransactionLine) query.uniqueResult();
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

        return particularTransactionLine;
    }

    //Method to update the Customer
    public void updateTransactionLine(TransactionLine transactionLine)
    {
        try{
            transaction = session.beginTransaction();
            session.update(transactionLine);
            System.out.println("Customer with id= " + transactionLine.getId() + " has been successfully updated.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    //Find All TransactionLine
    public List<TransactionLine> populateTable(long transactionId)
    {

        List<TransactionLine> particularTransactionLineList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine WHERE transactions.id= :transactionId").setParameter("transactionId" ,transactionId);
            particularTransactionLineList =  query.list();

            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularTransactionLineList;
    }

    public void updateTransactionLineAddButton(long id)
    {
        TransactionLine transactionLine = getTransactionLineById(id);
        //tranLine.setId(line.getId());

            transactionLine.getProducts();
            transactionLine.getTransactions();
            transactionLine.setQuantity(transactionLine.getQuantity() + 1);
            transactionLine.setTotal(transactionLine.getQuantity() * transactionLine.getProducts().getPrice());
            updateTransactionLine(transactionLine);


    }

    public void updateTransactionLineRemoveButton(long id)
    {
        TransactionLine transactionLine = getTransactionLineById(id);
        //tranLine.setId(line.getId());
        System.out.println(transactionLine.getQuantity());
        if(transactionLine.getQuantity() < 1)
        {
            System.out.println("Deleting the transaction");
            deleteTransactionLine(transactionLine.getId());
        }
        else {
            transactionLine.getProducts();
            transactionLine.getTransactions();
            transactionLine.setQuantity(transactionLine.getQuantity() - 1);
            transactionLine.setTotal(transactionLine.getQuantity() * transactionLine.getProducts().getPrice());
            updateTransactionLine(transactionLine);
        }
    }

    public double totalForEachTransaction(long transaction_id)
    {
        List<TransactionLine> transactionLine = new ArrayList<>();
        LocalDate localDates = LocalDate.now();
        Transactions dbTransactions  = (Transactions) session.get(Transactions.class, transaction_id);

        double total = 0.00;
        try{
            String string = "Select SUM(total) from reversevendingmachine.transaction_line where transaction_id = " + transaction_id;
            transaction = session.beginTransaction();
            //Query query = session.createQuery("FROM TransactionLine WHERE transactions.id= :transaction_id").setParameter("transaction_id", transaction_id);
            Query query = session.createSQLQuery(string);
            //transactionLine =  query.list();
            if(query.uniqueResult() != null)
            {
                total = (Double) query.uniqueResult();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
        return total;
    }

    //Disable button when clicked
    public boolean buttonPressedProductOne(long productID)
    {
        boolean value = false;
        try{
            productID = getTransactionLineById(id).getProducts().getId();

            if(productID == 1)
                value = true;

            if(getTransactionLineById(id).getQuantity() <=0)
                value = false;

            //buttonValueChange("Item added to Cart");
        }
        catch(Exception e)
        {
          System.out.println(e.getMessage());
        }

        return value;
    }
    //Disable button when clicked
    public boolean buttonPressedProductTwo(Long productID)
    {
        boolean value = false;
        try{
            productID = getTransactionLineById(id).getProducts().getId();

            if(productID == 2)
                value = true;


            if(getTransactionLineById(id).getQuantity() <=0)
                value = false;

            //buttonValueChange("Item added to Cart");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return value;
    }
    //Disable button when clicked
    public boolean buttonPressedProductThree(Long productID)
    {
        boolean value = false;
        try{
            productID = getTransactionLineById(id).getProducts().getId();

            if(productID == 3)
                value = true;


            if(getTransactionLineById(id).getQuantity() <=0)
                value = false;

            //buttonValueChange("Item added to Cart");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return value;
    }
    //Find All TransactionLine
    public List<TransactionLine> populateTableAdmin()
    {

        List<TransactionLine> particularTransactionLineList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine");
            particularTransactionLineList =  query.list();

            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularTransactionLineList;
    }




}
