package com.example.kursach_demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {

    static int id1 = 0;

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty birth;
    private SimpleStringProperty department;
    private SimpleStringProperty jobTitle;
    private SimpleStringProperty startDate;

    public Item(String name, String birth, String department, String jobTitle, String startDate) {
        this.id = new SimpleIntegerProperty (id1++ + 1);
        this.name = new SimpleStringProperty (name);
        this.birth = new SimpleStringProperty (birth);
        this.department = new SimpleStringProperty (department);
        this.jobTitle = new SimpleStringProperty (jobTitle);
        this.startDate = new SimpleStringProperty (startDate);
    }

    public int getId() {
        return id.get();
    }
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public String getName() {
        return name.get();
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getBirth() {
        return birth.get();
    }
    public SimpleStringProperty birthProperty() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth.set(birth);
    }
    public String getDepartment() {
        return department.get();
    }
    public SimpleStringProperty departmentProperty() {
        return department;
    }
    public void setDepartment(String department) {
        this.department.set(department);
    }
    public String getJobTitle() {
        return jobTitle.get();
    }
    public SimpleStringProperty jobTitleProperty() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }
    public String getStartDate() {
        return startDate.get();
    }
    public SimpleStringProperty startDateProperty() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

}

