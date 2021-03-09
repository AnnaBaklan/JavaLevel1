package Lesson5;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Lesson5 {
    private static final int AGE_FOR_CHECK = 40;

    public static void main(String[] args) {

        List<Employee> employeesList = new ArrayList<Employee>();

        try {
        employeesList.add(new Employee("Sheldon Cooper", "doctorCooper@bbt.com", "+4356783", 100000, 43));
        employeesList.add(new Employee("Penny Hofstadter", "penny@bbt.com", "+43585476", 3000, 35));
        employeesList.add(new Employee("Leonard Hofstadter", "lenny@bbt.com", "+458745", 70000, 56));
        employeesList.add(new Employee("Howard Wolowitz", "howard@bbt.com", "+4569752", 50000, 41));
        employeesList.add(new Employee("Raj Koothrappali", "raj@bbt.com", "+18547953", 50000, 37));
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        printEmployeeByAge(employeesList);
    }

    private static void printEmployeeByAge(List<Employee> employeesList) {
            for (Employee employee: employeesList) {
                if (employee.getAge() >= AGE_FOR_CHECK) {
                    System.out.println(employee);
                }
            }
    }
}
