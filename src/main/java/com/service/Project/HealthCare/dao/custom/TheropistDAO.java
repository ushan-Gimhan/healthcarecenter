package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.bo.custom.TheropistBO;
import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Theropist;

import java.util.List;

public interface TheropistDAO extends CrudDAO<Theropist,String> {
    List<String> getAllId();
    Theropist getTheopistNameByid(String id);
}
