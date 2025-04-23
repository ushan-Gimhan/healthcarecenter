package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.PatientDAO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private final FactoryConfiguration config;

    {
        try {
            config = FactoryConfiguration.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> findAll() {
        Session session=config.getSession();
        Query<Patient> query = session.createQuery("FROM Patient ", Patient.class);
        return query.list();
    }

    @Override
    public Patient find(String id) {
        return null;
    }

    @Override
    public boolean save(Patient entity) {
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
    public boolean update(Patient entity) {
        Session session = config.getSession();
        Transaction tx = session.beginTransaction();

        try{
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
            session.remove(session.get(Patient.class, id));
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            return false;
        }
    }

    @Override
    public String generateId() {
        String newPatientId = "P001"; // Default ID if no records exist

        try (Session session = config.getSession()) {
            // Get the last patient ID
            Query<String> query = session.createQuery("SELECT id FROM Patient p ORDER BY p.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part of the ID
                String numericPart = lastId.substring(1);
                try {
                    int lastNumericId = Integer.parseInt(numericPart);
                    newPatientId = String.format("P%03d", lastNumericId + 1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Optionally log the error or handle gracefully
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPatientId;
    }
    public List<String> getAllPatientIds(){
        List<String> patientIds = new ArrayList<>();
        Session session = config.getSession();

        try {
            session.beginTransaction();

            List<String> results = session.createQuery("SELECT p.id FROM Patient p", String.class).getResultList();
            patientIds.addAll(results);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return patientIds;

    }

    @Override
    public Patient getPatientById(String id) {
        Session session = config.getSession();
        Patient patient = null;

        try {
            session = config.getSession(); // Your Hibernate session provider
            patient = session.get(Patient.class, id); // Fetch by primary key
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return patient;
    }

    @Override
    public Patient getPationByName(String name) {
        Session session = config.getSession();
        Patient patient = null;

        try {
            session = config.getSession(); // Your Hibernate session provider
            patient = session.get(Patient.class, name); // Fetch by primary key
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return patient;
    }
}
