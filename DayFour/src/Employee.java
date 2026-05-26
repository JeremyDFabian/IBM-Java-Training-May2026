public class Employee implements Comparable<Employee> {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int compareTo(Employee other) {
        return Double.compare(other.getSalary(), this.getSalary());
    }

    @Override
	public String toString() {
		return name + (" | ") + department + (" | $") + salary ;
	}
}

