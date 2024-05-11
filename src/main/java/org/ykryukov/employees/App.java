package org.ykryukov.employees;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class TestClass {
    public static void print(List<? super String> list) {
        list.add("Hello World!");
        System.out.println(list);
    }
}

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
                    null,
                    gen.generateBoolean(),
                    gen.generateDouble(),
                    gen.generateString(20)
            );
            employeeSet.add(employee);
        }
        return employeeSet;
    }

    private static void collisionTest(List<String> list) {
        System.out.println("collisionTest: ");
        Map<String, String> map = list.stream().collect(
                Collectors.toMap(el -> el.split("\\|")[0],
                        el -> el.split("\\|")[1],
                        (oldVal, newVal) -> oldVal + ", " + newVal, HashMap::new));
        System.out.println(map);
    }

    private static void internalFuncInterfaceTest(List<String> list) {
        System.out.println("internalFuncInterfaceTest: ");
//
//        Function<String, String> fx = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return null;
//            }
//        }
        Function<String, String> fx = (t) -> t.split("\\|")[0];
        System.out.println(fx.apply("1|2|3"));
        System.out.println(list.stream().map(fx).collect(Collectors.toList()));
    }

    private static void genericTest() {
        System.out.println("genericTest: ");
        Object ob = "Welcome";
        List<Object> list2 = new ArrayList<>();
        list2.add(ob);
        TestClass.print(list2);
    }

    private static List<String> getStrings() {
        List<String> list2 = new ArrayList<>();
        list2.add("id|name|position_at_work|fluent_english|salary|department|date_hiring|date_dismissal|manager");
        list2.add("1|Евгений|программист|1|3050|департамент разработки|||");
        list2.add("2|Анатолий|менеджер|0|2500|департамент консалтинга|||");
        list2.add("3|Надежда|аналитик|1|1300|департамент консалтинга|||");
        list2.add("4|Галина|менеджер|0|4000|департамент консалтинга|||");
        list2.add("5|Андрей|программист|0|3800|департамент разработки|||");
        list2.add("6|Екатерина|программист|0|2000|департамент разработки|||");
        list2.add("7|Надежда|менеджер|1|5000|департамент консалтинга|||");
        return list2;
    }

    private static void collectorsSamples(List<String> list2) {
        Function<String, String> fnStrParser = (str) -> str.split("\\|", -1)[1];
        Function<String, Boolean> fnFilter = (str) -> str.split("\\|", -1)[0].equals("id");

        // Collectors samples
        System.out.println("Collectors samples: ");
        System.out.println("---------------------------------------------------");
        // Accumulate names into a List
        //List<String> listNames = list2.stream().filter(el -> !fnFilter.apply(el)).map(el->el.split("\\|")[1]).collect(Collectors.toList());
        List<String> listNames = list2.stream().filter(el -> !fnFilter.apply(el)).map(fnStrParser).toList();
        System.out.println("Accumulate names into a List: ");
        System.out.println(listNames);
        System.out.println("---------------------------------------------------");
        // Accumulate names into a TreeSet
        SortedSet<String> listNamesSorted = list2.stream().filter(el -> !fnFilter.apply(el)).map(fnStrParser).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Accumulate names into a TreeSet: ");
        System.out.println(listNamesSorted);
        System.out.println("---------------------------------------------------");
        // Convert elements to strings and concatenate them, separated by semicolons
        String strNames = list2.stream().filter(el -> !fnFilter.apply(el)).map(fnStrParser).collect(Collectors.joining(";"));
        System.out.println("Convert elements to strings and concatenate them, separated by commas: ");
        System.out.println(strNames);
        System.out.println("---------------------------------------------------");
        // Compute sum of salaries of employees
        //Integer sum = list2.stream().filter(el -> !fnFilter.apply(el)).collect(Collectors.summingInt(el -> Integer.valueOf(el.split("\\|")[4])));
        int sum = list2.stream().filter(el -> !fnFilter.apply(el)).mapToInt(el -> Integer.parseInt(el.split("\\|", -1)[4])).sum();
        System.out.println("Compute sum of salaries of employees: " + sum);
        System.out.println("---------------------------------------------------");
        // Group employees by department
        Map<String, Set<String>> map = list2.stream().filter(el -> !fnFilter.apply(el)).collect(Collectors.groupingBy(el -> el.split("\\|", -1)[5], Collectors.toSet()));
        System.out.println("Group employees by department: ");
        System.out.println(map);
        System.out.println("---------------------------------------------------");
        // Compute sum of salaries by department
        Map<String, Integer> map2 = list2.stream().filter(el -> !fnFilter.apply(el)).collect(Collectors.groupingBy(el -> el.split("\\|", -1)[5],
                Collectors.summingInt(el -> Integer.parseInt(el.split("\\|", -1)[4]))));
        System.out.println("Compute sum of salaries by department: ");
        System.out.println(map2);
        System.out.println("---------------------------------------------------");
        // Partition employees by fluent english
        Map<Boolean, Set<String>> map3 = list2.stream().filter(el -> !fnFilter.apply(el)).collect(Collectors.partitioningBy(el -> el.split("\\|", -1)[3].equals("1"), Collectors.toSet()));
        System.out.println("Partition employees by fluent english: ");
        System.out.println(map3);
    }

    public static void main(String[] args) {
        Set<Employee<Integer>> employeeSet = getEmployees();
        List<Employee<Integer>> employeeList = Util.listStringToEmployee(getStrings());

        Report.printEmployeesGroupedByDOB(employeeSet);
        System.out.println("---------------------------------------------------");
        Report.printEmployeesGroupedByCountryAndCityResidence(employeeSet);
        System.out.println("---------------------------------------------------");
        Report.printEmployeesNames(employeeList);
        System.out.println("---------------------------------------------------");
        Report.printUniqNamesSeparatedByCommas(employeeList);
        System.out.println("---------------------------------------------------");
        Report.printSumOfSalaries(employeeList);
        System.out.println("---------------------------------------------------");
        Report.printGroupEmployeesByDepartment(employeeList);
        System.out.println("---------------------------------------------------");
        Report.printSumOfSalariesByDepartment(employeeList);
        System.out.println("---------------------------------------------------");
        Report.printPartitionEmployeesByFluentEnglish(employeeList);
        System.out.println("---------------------------------------------------");
        Report.printEmpMaxSalary(employeeList);

//        List<String> list = new ArrayList<>();
//        list.add("1|first");
//        list.add("2|second");
//        list.add("2|third");
//        list.add("4|fourth");
//
//        collisionTest(list);
//        internalFuncInterfaceTest(list);
//        genericTest();


        //collectorsSamples(getStrings());

    }
}
