package com.reversevending.databaseOperationsDAO;




import com.reversevending.domain.Customer;

import com.reversevending.hibernateUtil.HibernateUtil;
import com.reversevending.repository.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import com.reversevending.encryption.*;
import java.util.ArrayList;
import java.util.List;

//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class CustomerDAO implements Serializable, Repository<Customer> {
    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Long id;
    private static Long transactionsId;
    private static Customer customer;
    private CustomerDAO customerDAO;

    @Override
    public void add(Customer customer) {
        try {
            transaction = session.beginTransaction();
            session.save(customer);
            id = customer.getId();
            System.out.println("LATEST ID = " + id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    public String isEmailAvailable(String email)
    {
        String result = "failure";
        try{
            Customer particularCustomer = new Customer();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer Where email= :email").setParameter("email", email);
            particularCustomer = (Customer) query.uniqueResult();

            //System.out.println("*****************************" + particularCustomer.getEmail());
            if(particularCustomer == null)
            {
               result = "success";
               System.out.println("Email does not exist");
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email already exists!"));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }

        return result;
    }

    @Override
    public Customer getById(Long id) {
        Customer particularCustomer = new Customer();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE id= :customer_id").setParameter("customer_id", id);
            particularCustomer = (Customer) query.uniqueResult();
            //particularCustomerList = query.list();
            //System.out.println(particularCustomer.getPassword());

            System.out.println("Student with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findCustomerById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularCustomer;
    }

    @Override
    public void update(Customer customer) {
        try{
            transaction = session.beginTransaction();
            session.update(customer);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try{
            transaction = session.beginTransaction();
            Customer custId = (Customer) session.load(Customer.class, new Long(id));
            session.delete(custId);

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

    //Login to the
    public Customer login(String email, String password)
    {
            String result = "failure";
        try{

            Customer particularCustomer = new Customer();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE email= :email").setParameter("email", email);
            particularCustomer = (Customer) query.uniqueResult();

                    if(BCrypt.checkpw(password, particularCustomer.getPassword()))
                    {
                        System.out.println("It matches");
                        customer = particularCustomer;
                        id = customer.getId();
                    }
                    else //(particularCustomer == null )
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account not be found, register!"));
                    }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                transaction.commit();

            }
            return customer;
        }

    public String logout() {
        session.close();
        return "login.xhtml";
    }

    public Long getCustomerID()
    {
        return id;
    }

    public void preloadData()
    {
        List<Customer> reading = readData("admin");
        if(!reading.isEmpty()){
            System.out.println("Customer already exists in the database");
        }
        else{
            try{
                Customer customer = new Customer();
                customer.setName("admin");
                customer.setSurname("admin");
                customer.setEmail("admin@admin.co.za");
                customer.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt(12)));
                customer.setContact("0110555444");
                transaction = session.beginTransaction();
                session.save(customer);
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

    public List<Customer> readData(String name) {

        List<Customer> particularCustomerList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE name= :customer_name").setParameter("customer_name", name);
            particularCustomerList = query.list();

            //XHTML reponse Text
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findCustomerById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularCustomerList;
    }

    public ArrayList<Customer> populateTable()
    {
        Customer particularCustomer = new Customer();

        ArrayList<Customer> particularCustomerList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer");
            //particularProduct =  query.list();
            particularCustomerList = (ArrayList<Customer>) query.list();

            //XHTML reponse Text
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularCustomerList;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }


    public String editCustomer(Long customerID)
    {
        System.out.println("******* In function******");
        id = customerID;
        return "adminEditCustomer.xhtml";
//        try{
//            System.out.println("(((((((((((((" + customerID);
//            redirect();
//
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return redirect();
    }
}
