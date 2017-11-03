package model;

import java.time.LocalDate;

public class Contact {
    private Integer idcontact;
    private String surname;
    private String firstname;
    private String middlename;
    private LocalDate firstdate;
    private LocalDate lastdate;
    private LocalDate birthdate;
    private String gender;
    private String marital;
    private String website;
    private String email;
    private String workplace;
    private String citizenship;
    private String urlAvatar;

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public Integer getIdcontact() {
        return this.idcontact;
    }

    public void setIdcontact(Integer idcontact) {
        this.idcontact = idcontact;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(LocalDate firstdate) {
        this.firstdate = firstdate;
    }

    public LocalDate getLastdate() {
        return lastdate;
    }

    public void setLastdate(LocalDate lastdate) {
        this.lastdate = lastdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @Override
    public String toString() {
        return "Contact : [contact_id = " + idcontact + " surname = " + surname;
    }
}
