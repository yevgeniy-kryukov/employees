package org.ykryukov.employees;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Util {
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static boolean parseBoolean(String val) {
        return switch (val) {
            case "true", "1" -> true;
            default -> false;
        };
    }

    public static List<Employee<Integer>> listStringToEmployee(List<String> list) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Function<String, Employee<Integer>> fnStrParser = (str) -> {
            String[] arr = str.split("\\|", -1);
            try {
                return Employee.<Integer>builder()
                        .id(Integer.parseInt(arr[0]))
                        .residenceAddress(null)
                        .cityOfResidence(null)
                        .countryOfResidence(null)
                        .DOB(null)
                        .firstName(arr[1])
                        .fatherName(null)
                        .lastName(null)
                        .dateDismissal(!arr[7].isEmpty() ? dateFormat.parse(arr[7]) : null)
                        .dateHiring(!arr[6].isEmpty() ? dateFormat.parse(arr[6]) : null)
                        .department(arr[5])
                        .fluentEnglish(parseBoolean(arr[3]))
                        .manager(null) //arr[8]
                        .positionAtWork(arr[2])
                        .salary(Double.parseDouble(arr[4]))
                        .build();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        };
        Function<String, Boolean> fnFilter = (str) -> str.split("\\|")[0].equals("id");
        return list.stream().filter(el -> !fnFilter.apply(el)).map(fnStrParser).collect(Collectors.toList());
    }
}
