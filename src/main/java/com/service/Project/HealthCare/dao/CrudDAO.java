package com.service.Project.HealthCare.dao;

import com.service.Project.HealthCare.entity.SuperEntity;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<T extends SuperEntity,id> extends SuperDAO{
    public List<T> findAll();
    public T find(String id);
    public boolean save(T entity);
    public boolean update(T entity) throws IOException;
    public boolean delete(String id);
    public String generateId();
}
