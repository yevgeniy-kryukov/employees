package org.ykryukov.employees;

import lombok.Builder;

import java.util.Date;

import static org.ykryukov.employees.Util.dateToString;

public class Person<I> {
    final private I id;
    private String LastName;
    private String FirstName;
    private String FatherName;
    private Date DOB;
    private String CountryOfResidence;
    private String CityOfResidence;
    private String ResidenceAddress;

    public Person(I id,
                  String lastName,
                  String firstName,
                  String fatherName,
                  Date DOB,
                  String countryOfResidence,
                  String cityOfResidence,
                  String residenceAddress) {
        this.id = id;
        this.LastName = lastName;
        this.FirstName = firstName;
        this.FatherName = fatherName;
        this.DOB = DOB;
        this.CountryOfResidence = countryOfResidence;
        this.CityOfResidence = cityOfResidence;
        this.ResidenceAddress = residenceAddress;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        CountryOfResidence = countryOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        CityOfResidence = cityOfResidence;
    }

    public void setResidenceAddress(String residenceAddress) {
        ResidenceAddress = residenceAddress;
    }

    public I getId() {
        return id;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getCountryOfResidence() {
        return CountryOfResidence;
    }

    public String getCityOfResidence() {
        return CityOfResidence;
    }

    public String getResidenceAddress() {
        return ResidenceAddress;
    }

    public String getFullName() {
        return this.LastName + " " + this.FirstName + " " + this.FatherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person<?> person)) return false;

        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", LastName='" + LastName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", FatherName='" + FatherName + '\'' +
                ", DOB=" + dateToString(DOB) +
                ", CountryOfResidence='" + CountryOfResidence + '\'' +
                ", CityOfResidence='" + CityOfResidence + '\'' +
                ", ResidenceAddress='" + ResidenceAddress + '\'' +
                '}';
    }
}
