package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.SessionDAO;
import com.service.Project.HealthCare.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {
    FactoryConfiguration config;

    {
        try {
            config = FactoryConfiguration.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TherapySession> findAll() {
        return List.of();
    }

    @Override
    public TherapySession find(String id) {
        return null;
    }

    @Override
    public boolean save(TherapySession entity) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();

        try{
            session.persist(entity);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(TherapySession entity) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();

        try{
            session.merge(entity);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();
        try{
            session.remove(session.get(TherapySession.class, id));
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            return false;
        }

    }

    @Override
    public String generateId() {
        String newSessionId = "S001"; // Default ID if no records exist

        try (Session session = config.getSession()) {
            // Get the last patient ID
            Query<String> query = session.createQuery("SELECT id FROM TherapySession  s ORDER BY s.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part of the ID
                String numericPart = lastId.substring(1);
                try {
                    int lastNumericId = Integer.parseInt(numericPart);
                    newSessionId = String.format("S%03d", lastNumericId + 1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Optionally log the error or handle gracefully
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSessionId;
    }
}
