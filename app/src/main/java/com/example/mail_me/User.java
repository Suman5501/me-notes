package com.example.mail_me;

public class User {

    public String fullName, birthDate , email;

    public User(){

    }

    public User(String fullName, String birthDate, String email){
        this.fullName = fullName;
        this.birthDate = birthDate;

        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
