import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class FileSorter_202 {

    public FileSorter_202() {
    }

    public void sortFile(String inputFilePath, String outputFilePath) {
        try {
            SortedSet<Employee> employees = readEmployees(inputFilePath);
            writeEmployees(employees, outputFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SortedSet<Employee> readEmployees(String inputFilePath) throws IOException {
        SortedSet<Employee> employees = new TreeSet<>(Comparator.comparingInt(o -> o.age));
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFilePath));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String s[] = line.split(",");
                String name = s[0];
                int age = Integer.parseInt(s[1].trim());
                char gender = s[2].charAt(0);
                employees.add(new Employee(name, age, gender));
            }
        } finally {
            closeSilently(reader);
        }
        return employees;
    }

    public void writeEmployees(SortedSet<Employee> employees, String outputFilePath) throws IOException {
        if (employees == null || employees.isEmpty()) {
            return;
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFilePath));
            for (Employee employee : employees) {
                writer.write(followCVSformat(employee.toString()));
                writer.newLine();
            }
        } finally {
            closeSilently(writer);
        }
    }

    public void closeSilently(AutoCloseable closeable) {
        if (Objects.isNull(closeable)) {
            return;
        }

        try {
            closeable.close();
        } catch (Exception e) {
            //ignore the exception as silent
        }
    }

    public String followCVSformat(String value) {
        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static class Employee {
        private String name;
        private int age;
        private char gender;

        public Employee(String name, int age, char gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return name + "," + age + "," + gender;
        }
    }
}
