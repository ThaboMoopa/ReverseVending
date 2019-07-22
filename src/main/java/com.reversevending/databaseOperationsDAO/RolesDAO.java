package com.reversevending.databaseOperationsDAO;

import com.reversevending.domain.Roles;
import com.reversevending.hibernateUtil.HibernateUtil;
import com.reversevending.repository.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RolesDAO implements Repository<Roles> {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void add(Roles roles) {

        //return null;
    }

    @Override
    public void update(Roles roles) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Roles getById(long id) {
        return null;
    }

    @Override
    public List<Roles> getAll() {
        List<Roles> roles = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Roles");
            roles = query.list();
        }
        catch(Exception e)
        {
            System.out.println("Exception from Roles");
        }

        System.out.println(roles);
        return roles;
    }

    public void preLoad()
    {
        try{
            //if the read of all Roles is empty then add new Roles if exists then skip
            List<Roles> rolesPreload = new ArrayList<>();
            rolesPreload.add(new Roles("Admin"));
            rolesPreload.add(new Roles("user"));

            for (Roles role : rolesPreload) {
                transaction = session.beginTransaction();
                session.save(role);
                transaction.commit();
            }
//            if(getAll().isEmpty()){
//
//            }
//            else {
//                System.out.println("The Roles table is empty");
//            }
        }
        catch(Exception e)
        {

        }
    }
}
