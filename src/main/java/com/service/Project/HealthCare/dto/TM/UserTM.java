package com.service.Project.HealthCare.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTM {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private String role;
}
