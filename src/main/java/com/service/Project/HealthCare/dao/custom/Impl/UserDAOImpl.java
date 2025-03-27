package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.UserDAO;
import com.service.Project.HealthCare.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final FactoryConfiguration config=FactoryConfiguration.getInstance();

    public UserDAOImpl() throws IOException {
    }

    @Override
    public User findByuserNAme(String username) {
        Session session=config.getSession();
        Query<User> query = session.createQuery("FROM User a WHERE a.userName = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public List<User> findAll() {
        Session session=config.getSession();
        Query<User> query = session.createQuery("FROM User", User.class);
        return query.list();
    }

    @Override
    public User find(String id) {
        return null;
    }

    @Override
    public boolean save(User entity) {
        Session session=config.getSession();
        Transaction tx=session.beginTransaction();
        if(entity!=null){
            session.persist(entity);
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(User entity)  {
        Session session = config.getSession();
        Transaction tx=session.beginTransaction();

        if(entity!=null){
            session.merge(entity);
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }


    }

    @Override
    public boolean delete(String id) {
        Session session = config.getSession();
        Transaction tx=session.beginTransaction();

        if(id!=null){
            session.remove(session.get(User.class, id));
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }
    }

    @Override
    public String genarateId() {
        String newCustomerId = "A001"; // Default if no records exist

        try (Session session = config.getSession()) {
            // Get the last customer ID
            Query<String> query = session.createQuery("SELECT id FROM User a ORDER BY a.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                // Extract the numeric part of the ID
                int lastNumericId = Integer.parseInt(lastId.substring(1));
                newCustomerId = String.format("A%03d", lastNumericId + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCustomerId;
    }
}
