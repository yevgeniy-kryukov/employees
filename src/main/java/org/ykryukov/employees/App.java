package org.ykryukov.employees;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class TestClass {
    public static void print(List<? super String> list) {
        list.add("Hello World!");
        System.out.println(list.get(0));
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
                    null
            );
            employeeSet.add(employee);
        }
        return employeeSet;
    }

    private static void collisianTest(List<String> list) {
        System.out.println("collisianTest: ");
        Map<String, String> map = list.stream().collect(Collectors.toMap(el -> el.split("\\|")[0], el -> el.split("\\|")[1], (oldVal, newVal) -> oldVal + ", " + newVal, HashMap::new));
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
        List<String> list2 = new ArrayList<>();
        //list2.add("Hello World!");
        TestClass.print(list2);
    }

    public static void main(String[] args) {
        Set<Employee<Integer>> employeeSet = getEmployees();

        Report.printEmployeesGroupedByDOB(employeeSet);
        System.out.println("---------------------------------------------------");
        Report.printEmployeesGroupedByCountryAndCityResidence(employeeSet);

        List<String> list = new ArrayList<>();
        list.add("1|first");
        list.add("2|second");
        list.add("2|third");
        list.add("4|fourth");

        collisianTest(list);
        internalFuncInterfaceTest(list);
        genericTest();

        //employee.setLastName("test1");
        //System.out.println(employee);
    }
}
