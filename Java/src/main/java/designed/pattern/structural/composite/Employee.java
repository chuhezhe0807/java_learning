package designed.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Employee
 * Package: designed.pattern.structural.composite
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/19 22:35
 * @Version 1.0
 */
public class Employee {
    private String name;
    private String departNumber;
    private long salary;

    private List<Employee> employeeList;

    public Employee(String name, String departNumber, long salary) {
        this.name = name;
        this.departNumber = departNumber;
        this.salary = salary;
        this.employeeList = new ArrayList<>();
    }

    public void add(Employee e) {
        employeeList.add(e);
    }

    public void remove(Employee e) {
        employeeList.remove(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartNumber() {
        return departNumber;
    }

    public void setDepartNumber(String departNumber) {
        this.departNumber = departNumber;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", departNumber='" + departNumber + '\'' +
                ", salary=" + salary +
                '}';
    }
}
