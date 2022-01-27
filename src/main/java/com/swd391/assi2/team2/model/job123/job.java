package com.swd391.assi2.team2.model.job123;

import com.swd391.assi2.team2.model.DataModel;

import java.util.Date;

public class job extends DataModel {
    public String id;
    public String position; // emp,CEO,CTO,...
    public String salary;
    public String type; // full-time,part-time,work from home
    public String quantity; // number of employees to hire
    public String experience; // exp
    public String application_deadline;

    public job(String id, String position, String salary, String type, String quantity, String experience, String application_deadline) {
        this.id = id;
        this.position = position;
        this.salary = salary;
        this.type = type;
        this.quantity = quantity;
        this.experience = experience;
        this.application_deadline = application_deadline;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getApplication_deadline() {
        return application_deadline;
    }

    public void setApplication_deadline(String application_deadline) {
        this.application_deadline = application_deadline;
    }
}
