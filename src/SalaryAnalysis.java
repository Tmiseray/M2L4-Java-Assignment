import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double increaseSalary() {
        this.salary += salary * .1;
        return salary;
    }
}



public class SalaryAnalysis {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Kevin", 42, 150000),
                new Employee("Allen", 32, 90000),
                new Employee("Brandon", 21, 32000),
                new Employee("Mary", 63, 48000),
                new Employee("Taylor", 32, 47000)
        );

        System.out.println("\nProcessing Salaries > $50,000 ...");
        employees.stream()
                .filter(employee -> employee.getSalary() > 50000)
                .map(Employee::getName)
                .forEach(name -> System.out.println("Name: " + name));


        System.out.println("\nProcessing Average Salary for Employees +30 Years Old ...");
        OptionalDouble avgSalary = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .mapToDouble(Employee::getSalary)
                .average();
        System.out.println("Calculated Average Salary: $" + avgSalary.orElse(0));

        System.out.println("\nCalculating Salary Increase ...");

        employees.stream()
                .forEach(employee -> employee.setSalary(employee.increaseSalary()));
        List<Employee> newList = employees.stream()
                .collect(Collectors.toList());

        newList.stream()
                .forEach(
                        (employee -> System.out.println(
                                "\nName: " + employee.getName()
                                        + "\n\tAge: " + employee.getAge()
                                        + "\n\t\tSalary: " + employee.getSalary()
                                )
                        )
                );
    }
}