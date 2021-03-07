package Lesson5;

public class Lesson5 {
    private static final int AGE_FOR_CHECK = 40;
    private static final int EMPLOYEES_COUNT = 5;

    public static void main(String[] args) {

        Employee[] employees = new Employee[EMPLOYEES_COUNT];

        try {
            employees[0] = new Employee("Sheldon Cooper", "doctorCooper@bbt.com", "+4356783", 100000, 43);
            employees[1] = new Employee("Penny Hofstadter", "penny@bbt.com", "+43585476", 3000, 35);
            employees[2] = new Employee("Leonard Hofstadter", "lenny@bbt.com", "+458745", 70000, 56);
            employees[3] = new Employee("Howard Wolowitz", "howard@bbt.com", "+4569752", 50000, 41);
            employees[4] = new Employee("Raj Koothrappali", "raj@bbt.com", "+18547953", 50000, 37);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        printEmployeeByAge(employees);
    }

    private static void printEmployeeByAge(Employee[] employees) {
        try {
            for (Employee employee: employees) {
                if (employee.getAge() >= AGE_FOR_CHECK) {
                    System.out.println(employee);
                }
            }
        } catch (Exception e) {
            System.out.println("Сотрудник не задан");
        }
    }
}
