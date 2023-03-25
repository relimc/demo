package chapter05.part7.segment6;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 1, 23);
        Method m1 = Employee.class.getMethod("getName");
        Method m2 = Employee.class.getMethod("getSalary");
        Method m3 = Employee.class.getMethod("raiseSalary", double.class);

        String n = (String) m1.invoke(harry);  // 通过 invoke 调用 getName 方法
        System.out.println("harry's name is " + n);

        double s1 = (Double) m2.invoke(harry);  // 通过 invoke 调用 getSalary 方法
        System.out.println("harry's salary is " + s1);

        m3.invoke(harry, 30);  // 通过 invoke 调用 raiseSalary 方法
        double s2 = (Double) m2.invoke(harry);  // 通过 invoke 调用 getSalary 方法
        System.out.println("harry's salary is " + s2);
    }
}

class Employee
{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int year, int month, int day)
    {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public LocalDate getHireDay()
    {
        return hireDay;
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
