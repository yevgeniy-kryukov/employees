package org.ykryukov.employees;

import java.util.Date;

import static org.ykryukov.employees.Util.dateToString;

public class Employee<I> extends Person<I> implements Comparable<Employee> {
    private String positionAtWork;
    private Date dateHiring;
    private Date dateDismissal;
    private Person<I> manager;

    public Employee(I id,
                    String lastName,
                    String firstName,
                    String fatherName,
                    Date DOB,
                    String countryOfResidence,
                    String cityOfResidence,
                    String residenceAddress,
                    String positionAtWork,
                    Date dateHiring,
                    Date dateDismissal,
                    Person<I> manager) {
        super(id, lastName, firstName, fatherName, DOB, countryOfResidence, cityOfResidence, residenceAddress);
        this.positionAtWork = positionAtWork;
        this.dateHiring = dateHiring;
        this.dateDismissal = dateDismissal;
        this.manager = manager;
    }

    public String getPositionAtWork() {
        return positionAtWork;
    }

    public void setPositionAtWork(String positionAtWork) {
        this.positionAtWork = positionAtWork;
    }

    public Date getDateHiring() {
        return dateHiring;
    }

    public void setDateHiring(Date dateHiring) {
        this.dateHiring = dateHiring;
    }

    public Date getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(Date dateDismissal) {
        this.dateDismissal = dateDismissal;
    }

    public Person<I> getManager() {
        return manager;
    }

    public void setManager(Person<I> manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", LastName='" + getLastName() + '\'' +
                ", FirstName='" + getFirstName() + '\'' +
                ", FatherName='" + getFatherName() + '\'' +
                ", DOB=" + dateToString(getDOB()) +
                ", CountryOfResidence='" + getCountryOfResidence() + '\'' +
                ", CityOfResidence='" + getCityOfResidence() + '\'' +
                ", ResidenceAddress='" + getResidenceAddress() + '\'' +
                ", positionAtWork='" + positionAtWork + '\'' +
                ", dateHiring=" + dateToString(dateHiring) +
                ", dateDismissal=" + dateToString(dateDismissal) +
                ", manager=" + manager +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.getLastName().compareTo(o.getLastName());
    }
}
