package com.vanessa.diagnosis;

import java.util.ArrayList;

public class Doctor {

    private int id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String role;
    private String license;
    private String email;
    private String specialty;
    private String description;
    private String joined;
    private String location;

    public Doctor(int id, String firstName, String lastName, String mobile, String role, String license, String email, String specialty, String description, String joined, String location) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.role = role;
        this.license = license;
        this.email = email;
        this.specialty = specialty;
        this.description = description;
        this.joined = joined;
        this.location = location;
    }

    public Doctor() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", role='" + role + '\'' +
                ", license='" + license + '\'' +
                ", email='" + email + '\'' +
                ", specialty='" + specialty + '\'' +
                ", description='" + description + '\'' +
                ", joined='" + joined + '\'' +
                '}';
    }
}
