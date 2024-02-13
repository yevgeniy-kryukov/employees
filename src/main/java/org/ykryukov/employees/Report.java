package org.ykryukov.employees;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    public static void printEmployeesGroupedByDOB(Set<Employee<Integer>> employeeSet) {
        Map<Date, SortedSet<Employee<Integer>>> map = employeeSet.stream()
                .collect(Collectors.groupingBy(Person::getDOB,
                        Collectors.mapping(emp -> emp, Collectors.toCollection(TreeSet::new))));

        Map<Date, SortedSet<Employee<Integer>>> mapFiltered = map.entrySet().stream().filter(el->el.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("EmployeesGroupedByDOB: ");
        for (Map.Entry<Date, SortedSet<Employee<Integer>>> entry : mapFiltered.entrySet()) {
            System.out.println("DOB: " + Util.dateToString(entry.getKey()));
            for (Employee<Integer> emp : entry.getValue()) {
                System.out.println("Employee: " + emp);
            }
        }
    }
}
