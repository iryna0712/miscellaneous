import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Obama", Integer.MAX_VALUE, 1961, 8, 4);
        staff[2] = new Employee("Dracula", 2, 1431, 01, 01);

        try (PrintWriter out = new PrintWriter("employee.dat", StandardCharsets.UTF_8.displayName())) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner( new FileInputStream("employee.dat"), StandardCharsets.UTF_8.displayName())) {
            Employee[] newStaff = readData(in);

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    public static void writeData(Employee[] employees, PrintWriter out) throws IOException {
        out.println(employees.length);
        for (Employee e : employees) {
            writeEmployeeData(e, out);
        }
    }

    public static void writeEmployeeData(Employee e, PrintWriter out) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getBirth());
    }

    public static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine(); //consume \n character

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }

        return employees;
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        LocalDate birthDate = LocalDate.parse(tokens[2]);

        return new Employee(tokens[0], Integer.valueOf(tokens[1]), birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth());
    }


}
