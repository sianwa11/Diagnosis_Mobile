package com.vanessa.diagnosis;

public class Doctor {
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    private String licenseUrl;

    public Doctor(String firstName, String lastName, String email, String specialty, String licenseUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialty = specialty;
        this.licenseUrl = licenseUrl;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", licenseUrl'" + licenseUrl + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
