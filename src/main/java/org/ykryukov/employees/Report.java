package org.ykryukov.employees;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    public static void printEmployeesGroupedByDOB(Set<Employee<Integer>> employeeSet) {
        int counter = 0;
        Map<Date, SortedSet<Employee<Integer>>> map = employeeSet.stream()
                .collect(Collectors.groupingBy(Person::getDOB,
                        Collectors.mapping(emp -> emp, Collectors.toCollection(TreeSet::new))));

        Map<Date, SortedSet<Employee<Integer>>> mapFiltered = map.entrySet().stream().filter(el -> el.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("EmployeesGroupedByDOB: ");
        for (Map.Entry<Date, SortedSet<Employee<Integer>>> entry : mapFiltered.entrySet()) {
            if (counter > 10) {
                break;
            }
            counter++;
            System.out.println("DOB: " + Util.dateToString(entry.getKey()));
            for (Employee<Integer> emp : entry.getValue()) {
                System.out.println("Employee: " + emp);
            }
        }
    }

    public static void printEmployeesGroupedByCountryAndCityResidence(Set<Employee<Integer>> employeeSet) {
        int counter = 0;
//        Map<String, SortedSet<Employee<Integer>>> map = employeeSet.stream()
//                .collect(Collectors.groupingBy(el -> el.getCountryOfResidence() + ", " + el.getCityOfResidence(),
//                        Collectors.mapping(el -> el, Collectors.toCollection(TreeSet::new))));

        SortedMap<String, SortedSet<Employee<Integer>>> map = employeeSet.stream()
                .collect(Collectors.groupingBy(el -> el.getCountryOfResidence() + ", " + el.getCityOfResidence(), TreeMap::new,
                        Collectors.mapping(el -> el, Collectors.toCollection(TreeSet::new))));

        SortedMap<String, SortedSet<Employee<Integer>>> mapFiltered = map.entrySet().stream().filter(el -> el.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, TreeMap::new));

        System.out.println("printEmployeesGroupedByCountryAndCityResidence: ");
        for (Map.Entry<String, SortedSet<Employee<Integer>>> entry : mapFiltered.entrySet()) {
            if (counter > 10) {
                break;
            }
            counter++;
            System.out.println("Country, city: " + entry.getKey());
            for (Employee<Integer> emp : entry.getValue()) {
                System.out.println(emp);
            }
        }
    }

    public static void printEmployeesGroupedByDateHiring(Set<Employee<Integer>> employeeSet) {
        SortedMap<Date, SortedSet<Employee<Integer>>> hiringMap = employeeSet.stream()
                .collect(Collectors.groupingBy(Employee::getDateHiring, TreeMap::new,
                        Collectors.mapping(emp -> emp, Collectors.toCollection(TreeSet::new))));

        SortedMap<Date, SortedSet<Employee<Integer>>> hiringMapFiltered = hiringMap.entrySet().stream()
                .filter(emp -> emp.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, TreeMap::new));

        int counter = 0;
        System.out.println("printEmployeesGroupedByDateHiring: ");
        for (Map.Entry<Date, SortedSet<Employee<Integer>>> entry : hiringMapFiltered.entrySet()) {
            counter++;
            System.out.println("Date hiring: " + entry.getKey());
            for (Employee<Integer> emp : entry.getValue()) {
                System.out.println(emp);
            }
            if (counter > 10) {
                break;
            }
        }
    }
}
