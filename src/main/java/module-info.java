module com.service.Project.healthcare {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jbcrypt;

    opens com.service.Project.HealthCare.controller to javafx.fxml;
    opens com.service.Project.HealthCare.entity to org.hibernate.orm.core;
    opens com.service.Project.HealthCare.dto.TM to javafx.base;
    opens com.service.Project.HealthCare.config to javafx.fxml;
    exports com.service.Project.HealthCare;
    exports com.service.Project.HealthCare.controller;
}