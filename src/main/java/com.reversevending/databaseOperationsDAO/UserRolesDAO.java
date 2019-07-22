package com.reversevending.databaseOperationsDAO;

import com.reversevending.domain.Customer;
import com.reversevending.domain.Roles;
import com.reversevending.domain.UserRoles;
import com.reversevending.hibernateUtil.HibernateUtil;
import com.reversevending.repository.Repository;
import com.reversevending.repository.RepositoryDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRolesDAO implements RepositoryDAO<UserRoles> {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void add(UserRoles userRoles, long tid, long id) {
        try{
            transaction = session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, tid);
            Roles roles = (Roles) session.get(Roles.class, id);
            userRoles.setCustomer(customer);
            userRoles.setRole(roles);
            session.save(userRoles);
        }
        catch(Exception e)
        {

        }
        finally
        {
            transaction.commit();
        }
    }

    @Override
    public void update(UserRoles userRoles) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public UserRoles getById(long id) {
        return null;
    }

    @Override
    public List<UserRoles> getAll() {
        return null;
    }
}
