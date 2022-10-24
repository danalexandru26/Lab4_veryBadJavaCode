import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        try {
            readData(employees);
            loop(employees);
        } catch (IOException exception) {
            System.out.println("IO Exception");
        }
    }

    public static void loop(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("2 - Print Collection \n3 -  Print Employees w/ salary above 2500 \n4 - Print Employees in leading positions " +
                    "\n5 - Print Employees not in leading positions \n6 - Extract names written in all caps \n7 - Print salaries above 3000 \n8 - Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 2:
                    printObjects(employees);
                    break;
                case 3:
                    sortedPrintSalary(employees);
                    break;
                case 4:
                    printObjects(filterPrintA(employees));
                    break;
                case 5:
                    printObjects(filterPrintB(employees));
                    break;
                case 6:
                    filterPrintC(employees);
                    break;
                case 7:
                    filterPrintD(employees);
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Wrong selection");
                    break;
            }
        }
    }

    public static void readData(List<Employee> employees) throws IOException {
        File file = new File("in.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                employees.add(getObject(line));
            }
        }
    }

    public static Employee getObject(String line) {
        String[] data = line.trim().split(";+");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return new Employee(data[0], data[1], LocalDate.parse(data[2], formatter), Float.parseFloat(data[3]));
    }

    public static void printObjects(List<Employee> employee) {
        employee.forEach(System.out::println);
    }

    public static void sortedPrintSalary(List<Employee> employees) {
        employees.stream().filter((Employee p) -> p.getSalary() > 2500.0).forEach(System.out::println);
    }

    public static List<Employee> filterPrintA(List<Employee> employees) {
        return employees.stream().filter((Employee e) -> e.employmentDate.getMonth().getValue() == 4 && (e.getJob().contains("director") || e.getJob().contains("sef"))).collect(Collectors.toList());
    }

    public static List<Employee> filterPrintB(List<Employee> employees) {
        return employees.stream().filter((Employee e) -> !(e.getJob().contains("director") || e.getJob().contains("sef"))).sorted((Employee a, Employee b) -> Double.compare(b.getSalary(), a.getSalary())).collect(Collectors.toList());
    }

    public static void filterPrintC(List<Employee> employees) {
        employees.stream().filter((Employee e) -> e.getName().toUpperCase().equals(e.getName())).map(Employee::getName).forEach(System.out::println);
    }

    public static void filterPrintD(List<Employee> employees) {
        employees.stream().filter((Employee e) -> e.getSalary() < 3000).map(Employee::getSalary).forEach(System.out::println);
    }
}