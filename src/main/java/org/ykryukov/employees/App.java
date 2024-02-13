package org.ykryukov.employees;

import java.util.*;

public class App {
    private static Set<Employee<Integer>> getEmployees() {
        Set<Employee<Integer>> employeeSet = new HashSet<>();
        Generable gen = new Generator();
        for (int i = 0; i < 200; i++) {
            Employee<Integer> employee = new Employee<Integer>(
                    gen.generateInt(),
                    gen.generateString(10),
                    gen.generateString(10),
                    gen.generateString(10),
                    gen.generateDate(),
                    gen.generateString(10),
                    gen.generateString(10),
                    gen.generateString(25),
                    gen.generateString(15),
                    gen.generateDate(),
                    gen.generateDate(),
                    null
            );
            employeeSet.add(employee);
        }
        return employeeSet;
    }

    public static void main(String[] args) {
        Set<Employee<Integer>> employeeSet = getEmployees();

        Report.printEmployeesGroupedByDOB(employeeSet);

        //employee.setLastName("test1");
        //System.out.println(employee);
    }
}
