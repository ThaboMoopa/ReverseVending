package com.reversevending.hibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil{

    private static SessionFactory session = buildSessionFactoryObj();

    public static SessionFactory buildSessionFactoryObj(){

        try{
            session =  new Configuration().configure().buildSessionFactory();
        }
        catch(ExceptionInInitializerError e)
        {
            e.printStackTrace();
        }
        return session;
    }


    public static SessionFactory getSessionFactory()
    {
        return session;
    }
}
