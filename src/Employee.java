import java.time.LocalDate;

public class Employee{
    public Employee(String name, String job, LocalDate employmentDate, float salary) {
        this.name = name;
        this.job = job;
        this.employmentDate = employmentDate;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;

    }

    public void setName(String name) {
        this.name = name;

    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;

    }

    @Override public String toString() {
        return String.format("Name: %s, Job: %s, Employment date: %s, Salary: %f",
                name, job, employmentDate, salary);
    }

    String name;
    String job;
    LocalDate employmentDate;
    float salary;
}
