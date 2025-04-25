package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.TheropistDAO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Theropist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TheropistDAOImpl implements TheropistDAO {
    private final FactoryConfiguration config;

    {
        try {
            config = FactoryConfiguration.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Theropist> findAll() {
        Session session=config.getSession();
        Query<Theropist> query = session.createQuery("FROM Theropist ", Theropist.class);
        return query.list();
    }

    @Override
    public Theropist find(String id) {
        return null;
    }

    @Override
    public boolean save(Theropist entity) {
        Session session = config.getSession();
        Transaction tx = session.beginTransaction();

        try{
            session.persist(entity);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Theropist entity) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();

        try {
            session.merge(entity);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();

        try {
            session.remove(session.get(Theropist.class, id));
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public String generateId() {
        String newTheropistId = "T001"; // Default ID if no records exist

        try (Session session = config.getSession()) {
            // Get the last patient ID
            Query<String> query = session.createQuery("SELECT id FROM Theropist t ORDER BY t.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part of the ID
                String numericPart = lastId.substring(1);
                try {
                    int lastNumericId = Integer.parseInt(numericPart);
                    newTheropistId = String.format("T%03d", lastNumericId + 1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Optionally log the error or handle gracefully
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTheropistId;
    }

    @Override
    public List<String> getAllId() {
        List<String> theroPists = new ArrayList<>();
        Session session = config.getSession();

        try {
            session.beginTransaction();

            List<String> results = session.createQuery("SELECT p.id FROM Theropist p", String.class).getResultList();
            theroPists.addAll(results);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return theroPists;
    }

    @Override
    public Theropist getTheopistNameByid(String id) {
        Session session = config.getSession();
        Theropist theropist = null;

        try {
            session = config.getSession(); // Your Hibernate session provider
            theropist = session.get(Theropist.class, id); // Fetch by primary key
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return theropist;
    }
}
