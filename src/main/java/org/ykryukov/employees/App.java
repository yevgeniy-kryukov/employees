package org.ykryukov.employees;

public class App
{
    public static void main( String[] args )
    {
        Generable gen = new Generator();
        Person<Integer> employee = new Employee<Integer>(
                gen.generateInt(),
                gen.generateString(25),
                gen.generateString(25),
                gen.generateString(25),
                gen.generateDate(),
                gen.generateString(25),
                gen.generateString(25),
                gen.generateString(25),
                gen.generateString(25),
                gen.generateDate(),
                gen.generateDate(),
                null
        );

        employee.setLastName("test1");
        System.out.println(employee);
    }
}
