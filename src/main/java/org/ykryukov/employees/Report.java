package org.ykryukov.employees;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    public static void printEmployeesGroupedByDOB(final Set<Employee<Integer>> employeeSet) {
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

    public static void printEmployeesGroupedByCountryAndCityResidence(final Set<Employee<Integer>> employeeSet) {
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

    public static void printEmployeesGroupedByDateHiring(final Set<Employee<Integer>> employeeSet) {
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

    public static void printEmployeesNames(final List<Employee<Integer>> employeeList) {
        //final List<String> namesList = employeeList.stream().map(el -> el.getFirstName()).collect(Collectors.toList());
        final List<String> namesList = employeeList.stream().map(Person::getFirstName).toList();
        final SortedMap<String, Long> namesGroups = namesList.stream().collect(Collectors.groupingBy(el -> el, TreeMap::new, Collectors.counting()));
        System.out.println("printEmployeesNames: ");
        System.out.println(namesGroups);
    }

    public static void printUniqNamesSeparatedByCommas(final List<Employee<Integer>> employeeList) {
        final String str = employeeList.stream().map(Person::getFirstName).distinct().sorted().collect(Collectors.joining(":"));
        System.out.println("printUniqNamesSeparatedByCommas: " + str);
    }

    public static void printSumOfSalaries(final List<Employee<Integer>> employeeList) {
        //final double sum = employeeList.stream().collect(Collectors.summingDouble(el->el.getSalary()));
        final double sum = employeeList.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("printSumOfSalaries: " + sum);
    }

    public static void printGroupEmployeesByDepartment(final List<Employee<Integer>> employeeList) {
        final SortedMap<String, SortedSet<Employee<Integer>>> employeesByDepartmentMap = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.mapping(el -> el, Collectors.toCollection(TreeSet::new))));
        System.out.println("printGroupEmployeesByDepartment: ");
        System.out.println(employeesByDepartmentMap);
    }

    public static void printSumOfSalariesByDepartment(final List<Employee<Integer>> employeeList) {
//        final SortedMap<String, Double> salaryByDepartmentMap = employeeList.stream()
//                .collect(Collectors.groupingBy(el -> el.getDepartment(), TreeMap::new, Collectors.summingDouble(el -> el.getSalary())));
        final SortedMap<String, Double> salaryByDepartmentMap = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.summingDouble(Employee::getSalary)));
        System.out.println("printSumOfSalariesByDepartment: ");
        System.out.println(salaryByDepartmentMap);
    }

    public static void printPartitionEmployeesByFluentEnglish(final List<Employee<Integer>> employeeList) {
        final Map<Boolean, SortedSet<Employee<Integer>>> fluentEngMap = employeeList.stream()
                .collect(Collectors.partitioningBy(Employee::isFluentEnglish, Collectors.toCollection(TreeSet::new)));
        System.out.println("printPartitionEmployeesByFluentEnglish: ");
        System.out.println(fluentEngMap);
    }

    public static void printEmpMaxSalary(final List<Employee<Integer>> employeeList) {
        //final Optional<Employee<Integer>> obj = employeeList.stream().max(Comparator.comparingDouble(el -> el.getSalary()));
        //final Optional<Employee<Integer>> obj = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(el -> el.getSalary())));
        //final Optional<Double> obj = employeeList.stream().map(el -> el.getSalary()).max(Comparator.comparingDouble(el -> el));
        final OptionalDouble obj = employeeList.stream().mapToDouble(el -> el.getSalary()).max();
        if (obj.isPresent()) {
            System.out.println("printEmpMaxSalary: " + obj.getAsDouble()); //if (obj.getClass() == OptionalDouble.class)
            //System.out.println("printEmpMaxSalary: " + obj.get());
        } else {
            System.out.println("printEmpMaxSalary: not found!");
        }
    }

}