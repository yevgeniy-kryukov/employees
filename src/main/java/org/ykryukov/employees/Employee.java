package org.ykryukov.employees;

import lombok.Builder;

import java.util.Date;

import static org.ykryukov.employees.Util.dateToString;

public class Employee<I> extends Person<I> implements Comparable<Employee<I>> {
    private String positionAtWork;
    private Date dateHiring;
    private Date dateDismissal;
    private Person<I> manager;
    private boolean fluentEnglish;
    private double salary;
    private String department;

    @Builder
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
                    Employee<I> manager,
                    boolean fluentEnglish,
                    double salary,
                    String department) {
        super(id, lastName, firstName, fatherName, DOB, countryOfResidence, cityOfResidence, residenceAddress);
        this.positionAtWork = positionAtWork;
        this.dateHiring = dateHiring;
        this.dateDismissal = dateDismissal;
        this.manager = manager;
        this.fluentEnglish = fluentEnglish;
        this.salary = salary;
        this.department = department;
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

    public boolean isFluentEnglish() {
        return fluentEnglish;
    }

    public void setFluentEnglish(boolean fluentEnglish) {
        this.fluentEnglish = fluentEnglish;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
                ", fluentEnglish=" + fluentEnglish +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    @Override
    public int compareTo(Employee emp) {
        int res = 0;
        final String thisLastName = this.getLastName() == null ? "" : this.getLastName();
        final String otherLastName = emp.getLastName() == null ? "" : emp.getLastName();
        res = thisLastName.compareTo(otherLastName);
        if (res == 0) {
            final String thisFirstName = this.getFirstName() == null ? "" : this.getFirstName();
            final String otherFirstName = emp.getFirstName() == null ? "" : emp.getFirstName();
            res = thisFirstName.compareTo(otherFirstName);
        }
        return res;
    }
}
