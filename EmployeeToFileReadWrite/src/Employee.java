import java.time.DateTimeException;
import java.time.LocalDate;

public class Employee {

    private String name;
    private int salary;
    private LocalDate birth;

    public Employee(String name, int salary, int year, int month, int day) throws DateTimeException {
        this.name = name;
        this.salary = salary;
        this.birth = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public LocalDate getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return getName() + "|" + getSalary() + "|" + getBirth();
    }
}

