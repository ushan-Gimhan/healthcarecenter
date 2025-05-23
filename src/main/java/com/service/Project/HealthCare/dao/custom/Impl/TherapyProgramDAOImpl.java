package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.TherapyProgramDAO;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    private final FactoryConfiguration config;

    {
        try {
            config = FactoryConfiguration.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Programs> findAll() {
        Session session=config.getSession();
        Query<Programs> query = session.createQuery("FROM Therapy_Program ", Programs.class);
        return query.list();
    }

    @Override
    public Programs find(String id) {
        return null;
    }

    @Override
    public boolean save(Programs entity) {
        Session session=config.getSession();
        Transaction tx=session.beginTransaction();
        try {
            session = config.getSession(); // or sessionFactory.openSession()
            tx = session.beginTransaction();

            // Your DB operations here
            session.persist(entity); // or update, remove, etc.

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;

        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public boolean update(Programs entity) {
        Session session = config.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session = config.getSession(); // or sessionFactory.openSession()
            tx = session.beginTransaction();

            // Your DB operations here
            session.merge(entity); // or update, remove, etc.

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;

        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();
        try {
            Programs entity = session.get(Programs.class, id);
            session.remove(entity);
            tx.commit();
            return true;
        }catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    @Override
    public String generateId() {
        String newTheropyProgramId = "MT001"; // Default ID if no records exist

        try (Session session = config.getSession()) {
            Query<String> query = session.createQuery("SELECT p.id FROM Therapy_Program p ORDER BY p.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null && lastId.startsWith("MT")) {
                String numericPart = lastId.substring(2); // Skip "MT"
                try {
                    int lastNumericId = Integer.parseInt(numericPart);
                    newTheropyProgramId = String.format("MT%03d", lastNumericId + 1);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid numeric part in ID: " + numericPart);
                    // You may choose to log or use a default ID
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newTheropyProgramId;
    }

    public List<String> getAllPrograms(){
        List<String> programList = new ArrayList<>();
        Session session = config.getSession();

        try {
            session.beginTransaction();

            List<String> results = session.createQuery("SELECT p.pName FROM Therapy_Program p", String.class).getResultList();
            programList.addAll(results);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return programList;
    }

    @Override
    public Programs getProgramByName(String name) {
        Session session = config.getSession();
        Query<Programs> query = session.createQuery("FROM Therapy_Program a WHERE a.pName = :name", Programs.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }
}
