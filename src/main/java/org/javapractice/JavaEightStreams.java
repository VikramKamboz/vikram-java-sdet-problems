package org.javapractice;

import org.javapractice.models.Employees;
import org.javapractice.models.TestResult;

import java.util.*;
import java.util.stream.Collectors;

public class JavaEightStreams {
    static List<Employees> employees = createEmployeesDb();

    public static List<Employees> createEmployeesDb() {
        List<Employees> employees = Arrays.asList(
                new Employees("Alice", "Engineering", 60000.78, 25),
                new Employees("Carol", "HR", 70000.890, 41),
                new Employees("Eve", "Engineering", 48000, 54),
                new Employees("David", "Sales", 55000, 37),
                new Employees("Bob", "HR", 45000.89, 67)
        );
        return employees;
    }

    /*    Given List<Employee> with name, age, salary — filter employees earning more than 50000 and return their names as a List.
            Input:  [{Alice,25,60000},{Bob,30,40000},{Carol,28,75000}]
            Output: [Alice, Carol]*/

    private static void employeesMoreThan50000Salary() {
        List<String> namesList = employees.stream().filter(a -> a.getSalary() > 50000).map(a -> a.getName()).collect(Collectors.toList());
        System.out.println("namesList : " + namesList);
    }

/*    P12: Find Employee with Highest Salary   ·  Easy · Streams
    Given List<Employee>, find the employee with the highest salary using streams.
            Input:  [{Alice,60000},{Bob,40000},{Carol,75000}]
    Output: Carol*/

    private static void employeeWithHigherSalary() {
        String higherSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employees::getSalary))
                .map(a -> a.getName())
                .orElse("no employees found");
        System.out.println("employeeWithHigherSalary : " + higherSalaryEmployee);
    }

/*    Given List<Employee>, concatenate all names into a single comma-separated string.
    Input:  [{Alice},{Bob},{Carol}]
    Output: "Alice, Bob, Carol"*/
    private static void concatenateAllNames() {
        String concatenate = employees.stream().map(a -> a.getName()).collect(Collectors.joining(","));
        System.out.println("concatenate : " + concatenate);
    }

/*    P14: Calculate Average Age   ·  Easy · Streams
    Given List<Employee> with age field, calculate the average age using streams.
    Input:  [{Alice,25},{Bob,30},{Carol,28}]
    Output: 27.67*/

    private static void averageSalary() {
        double averageSalary = employees.stream().mapToDouble(a -> a.getAge()).average().orElse(0.0);;
        System.out.println("averageSalary : " + averageSalary);
    }

/*
    P15: Group Test Results by Status   ·  Medium · Streams
    Given a list of TestResult objects with id and status (PASS/FAIL/SKIP), group by status returning Map<String, List<String>> of IDs.
    Input:  [{TC001,PASS},{TC002,FAIL},{TC003,PASS},{TC004,SKIP}]
    Output: {PASS=[TC001,TC003], FAIL=[TC002], SKIP=[TC004]}
*/
    private static void groupTestResult() {
        List<TestResult> testResults = Arrays.asList(
                new TestResult("TC001", "PASS"),
                new TestResult("TC002", "FAIL"),
                new TestResult("TC003", "PASS"),
                new TestResult("TC004", "FAIL"),
                new TestResult("TC005", "FAIL")
        );

        Map<String, List<String>> groupTestResult = testResults.stream()
                .collect(Collectors.groupingBy(
                        TestResult::getStatus,
                        Collectors.mapping(TestResult::getId, Collectors.toList())
                ));
        System.out.println("groupTestResult : " + groupTestResult);
    }

/*    P16: Find Second Highest Salary   ·  Medium · Streams
    Given List<Employee>, find the second highest salary using streams. Handle ties — distinct salaries only.
            Input:  [60000, 75000, 45000, 75000, 55000]
    Output: 60000*/

    private static void secondHighestSalary() {
        double secondHighestSalary = employees.stream().mapToDouble(Employees::getSalary).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0.0);
        System.out.println("secondHighestSalary : " + secondHighestSalary);
    }

/*    P17: Top 3 Highest Paid Under 30   ·  Medium · Streams
    Find names of top 3 highest-paid employees under the age of 30, sorted by salary descending.
    Input:  [{Alice,25,60000},{Bob,35,90000},{Carol,28,75000},{Dave,27,80000},{Eve,29,70000}]
    Output: [Dave, Carol, Eve]*/

    private static void top3HighestPaidUnder30() {
        List<String> top3HighestPaidUnder30 = employees.stream()
                .filter(emp -> emp.getAge()<30)
                .sorted(Comparator.comparingDouble(Employees::getSalary)
                .reversed())
                .limit(3)
                .map(Employees::getName).collect(Collectors.toList());

        System.out.println("top3HighestPaidUnder30 : " + top3HighestPaidUnder30);
    }




    public static void main(String[] args) {
        employeesMoreThan50000Salary();
        employeeWithHigherSalary();
        concatenateAllNames();
        averageSalary();
        groupTestResult();
        secondHighestSalary();
        top3HighestPaidUnder30();

    }


}
