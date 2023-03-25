package chapter05.part7.segment4;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 1, 23);
        Class cl = harry.getClass();  // 获取 Employee 类对应的 Class 实例
        Field f = cl.getDeclaredField("name");  // 获取 name 字段对应的 Field 对象

        f.setAccessible(true);  // 获取对私有成员的访问权限

        System.out.println(f.get(harry));  // get 方法获取实例字段的值
        f.set(harry, "John");  // set 方法设置实例字段的值
        System.out.println(f.get(harry));  // 获取修改后的值
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
