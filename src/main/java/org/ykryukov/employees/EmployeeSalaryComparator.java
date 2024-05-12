package org.ykryukov.employees;

import java.util.Comparator;

public class EmployeeSalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) Math.ceil(o1.getSalary() - o2.getSalary());
    }
}
