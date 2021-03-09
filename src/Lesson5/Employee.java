package Lesson5;

import com.sun.org.apache.xml.internal.utils.WrongParserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

    private String fullName;
    private String email;
    private String phone;
    private int salary;
    private int age;
    private Pattern pattern;
    private Matcher matcher;
    private static final int MIN_AGE = 14;

    public Employee (String fullName, String email, String phone, int salary, int age) {
        setFullName(fullName);
        setEmail(email);
        setPhone(phone);
        setSalary (salary);
        setAge (age);
    }

   public String toString () {
        String newline = System.getProperty("line.separator");
        return "fullName:\t" + fullName  + newline + "\temail:\t" + email +  newline + "\tphone:\t" + phone + newline + "\tsalary:\t" + salary + newline + "\tage:\t" + age;
   }

   public int getAge () {
        return this.age;
   }

   public void setFullName(String fullName) {
       if (fullName.isEmpty()) {
           throw new NullPointerException("FullName should be filled");
       }
       this.fullName = fullName;
   }

    public void setEmail(String email) {

        if (email.isEmpty()) {
            return;
        }

        String emailRegExp = "^.+@[a-zA-Z]+\\.[a-zA-Z]+$";
        pattern = Pattern.compile(emailRegExp);
        matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new WrongParserException("Email "+ email + " for " + this.fullName + " is incorrect");
        }
        this.email = email;
    }

    public void setPhone(String phone) {
        if (phone.isEmpty()) {
            return;
        }
        String phoneRegExp = "\\+?[0-9]{4,}";
        pattern = Pattern.compile(phoneRegExp);
        matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            throw new WrongParserException("Phone "+ phone + " for " + this.fullName + " is incorrect");
        }
        this.phone = phone;
    }

    public void setSalary (int salary) {
        if (salary < 0) {
            throw new NullPointerException("Salary for " + this.fullName + " is negative");
        }
        this.salary = salary;
    }

    public void setAge (int age) {
        if (age < MIN_AGE) {
            throw new NullPointerException("Age for " + this.fullName + " is less than " + MIN_AGE);
        }
        this.age = age;
    }
}
