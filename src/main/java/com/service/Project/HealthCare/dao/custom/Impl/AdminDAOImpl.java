package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.AdminDAO;
import com.service.Project.HealthCare.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    private final FactoryConfiguration config=FactoryConfiguration.getInstance();

    @Override
    public Admin findByuserNAme(String username) {
        Session session=config.getSession();
        Query<Admin> query = session.createQuery("FROM Admin a WHERE a.userName = :username", Admin.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public List<Admin> findAll() {
        Session session=config.getSession();
        Query<Admin> query = session.createQuery("FROM Admin", Admin.class);
        return query.list();
    }

    @Override
    public Admin find(String id) {
        return null;
    }

    @Override
    public boolean save(Admin entity) {
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
    public boolean update(Admin entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
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
            session.remove(session.get(Admin.class, id));
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
            Query<String> query = session.createQuery("SELECT id FROM Admin a ORDER BY a.id DESC", String.class);
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
