package com.udacity.jwdnd.course1.cloudstorage.models;

public class SignupForm {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public SignupForm(String firstName, String lastName, String username, String password) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
