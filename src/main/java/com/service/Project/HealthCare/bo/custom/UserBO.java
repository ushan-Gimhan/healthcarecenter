package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserBO extends SuperBO {
    public List<UserDTO> findAll();
    public UserDTO find(String id);
    public boolean save(UserDTO entity);
    public boolean update(UserDTO entity);
    public boolean delete(String id);
    public String genareateID();
}
