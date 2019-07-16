package com.reversevending.databaseOperationsDAO;

import com.reversevending.domain.Products;
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

public class ProductsDAO implements Serializable {
    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Long id;


    //AddProducts to the database to the DB (Confirm if the product is in the database first)
    public void add()
    {
        List<Products> readProducts = readProducts("CANS");
        if(!readProducts.isEmpty()){
            System.out.println("Product already in the database");
        }
        else {
            try {

                List<Products> products = new ArrayList<>();
                products.add(new Products(1.20, "CANS", "Loading CANS"));
                products.add(new Products(1.30, "PAPER", "Loading PAPER"));
                products.add(new Products(2.00, "PLASTIC", "Loading PLASTIC"));

                for (Products product : products) {
                    transaction = session.beginTransaction();
                    session.save(product);
                    transaction.commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Long getProductID()
    {
        System.out.println(ProductsDAO.id);
        return id;
    }

    //Delete customer from DB
    public void deleteProduct(long id){
        try{
            transaction = session.beginTransaction();
            Products custId = (Products) session.load(Products.class, new Long(id));
            session.delete(custId);

            //XHTML response Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedId", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    public ArrayList<Products> populateTable()
    {
        Products particularProduct = new Products();

        ArrayList<Products> particularProductList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Products");
            //particularProduct =  query.list();
            particularProductList = (ArrayList<Products>) query.list();
            System.out.println("Products with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularProductList;
    }

    //Read Customer details
    public Products getProductById(long id){

        Products particularProduct = new Products();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Products WHERE id= :products_id").setParameter("products_id", id);
            particularProduct = (Products) query.uniqueResult();

            System.out.println("Products with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularProduct;
    }

    //Method to update the Customer
    public void updateProduct(Products product)
    {
        try{
            transaction = session.beginTransaction();
            session.update(product);
            System.out.println("Product with id= " + product.getId() + " has been successfully updated.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

        public List<Products> readProducts(String name){

        Products particularProduct = new Products();

        List<Products> particularProductList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Products WHERE name= :products_name").setParameter("products_name", name);
            particularProduct = (Products) query.uniqueResult();
            particularProductList = query.list();
            //XHTML reponse Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularProductList;


    }
}
