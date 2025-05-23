package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.RegistrationDAO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Payement;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    FactoryConfiguration config;

    {
        try {
            config = FactoryConfiguration.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Registration> findAll() {
        Session session=config.getSession();
        Query<Registration> query = session.createQuery("FROM Registration ", Registration.class);
        return query.list();
    }

    @Override
    public Registration find(String id) {
        return null;
    }

    @Override
    public boolean save(Registration entity) {
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
    public boolean update(Registration entity) {
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
            Registration entity = session.get(Registration.class, id);
            session.delete(entity);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public String generateId() {
        String newPaymentId = "R001"; // Default ID if no records exist

        try (Session session = config.getSession()) {
            // Get the last patient ID
            Query<String> query = session.createQuery("SELECT id FROM Registration  s ORDER BY s.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part of the ID
                String numericPart = lastId.substring(1);
                try {
                    int lastNumericId = Integer.parseInt(numericPart);
                    newPaymentId = String.format("R%03d", lastNumericId + 1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Optionally log the error or handle gracefully
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPaymentId;
    }

    @Override
    public Registration getPegistrationById(String id) {
        Session session = config.getSession();
        Registration registration = null;

        try {
            session = config.getSession(); // Your Hibernate session provider
            String hql = "FROM Registration r WHERE r.patient.id = :patientId";
            registration = session.createQuery(hql, Registration.class)
                    .setParameter("patientId", id)
                    .uniqueResult();// Fetch by primary key
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return registration;

    }
}

