package com.example.demo.entities;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer newsletterId;

    private String email;
    private String firstName;
    private String gender;
    private String stringBirthDate;
    private Date birthDate;
    private boolean flagForConsent;

    public Integer getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(Integer newsletterId) {
        this.newsletterId = newsletterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStringBirthDate() {
        return stringBirthDate;
    }

    public void setStringBirthDate(String stringBirthDate) {
        this.stringBirthDate = stringBirthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isFlagForConsent() {
        return flagForConsent;
    }

    public void setFlagForConsent(boolean flagForConsent) {
        this.flagForConsent = flagForConsent;
    }
}