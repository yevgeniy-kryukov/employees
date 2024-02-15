package org.ykryukov.employees;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    private static Set<Employee<Integer>> getEmployees() {
        Set<Employee<Integer>> employeeSet = new HashSet<>();
        Generable gen = new Generator();
        for (int i = 0; i < 50000; i++) {
            Employee<Integer> employee = new Employee<Integer>(
                    gen.generateInt(),
                    gen.generateString(10),
                    gen.generateString(10),
                    gen.generateString(10),
                    gen.generateDate(),
                    gen.generateString(3),
                    gen.generateString(3),
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
        System.out.println("---------------------------------------------------");
        Report.printEmployeesGroupedByCountryAndCityResidence(employeeSet);

//        List<String> list = new ArrayList<>();
//        list.add("1|first");
//        list.add("2|second");
//        list.add("2|third");
//        list.add("4|fourth");

//        Map<String, String> map = list.stream().collect(Collectors.toMap(el -> el.split("\\|")[0], el -> el.split("\\|")[1], (oldVal, newVal) -> oldVal + ", " + newVal, HashMap::new));
//        System.out.println(map);
//
//        System.out.println(list.stream().map(el -> el.split("\\|")[0]).collect(Collectors.toList()));

        //employee.setLastName("test1");
        //System.out.println(employee);
    }
}
