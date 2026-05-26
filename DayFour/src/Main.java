import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	
	public static void main(String[] args) {
		
	List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("Alice", "IT", 55000));
    employees.add(new Employee("Bob", "Finance", 60000));
    employees.add(new Employee("Alice", "HR", 50000)); // duplicate name
    employees.add(new Employee("Ken", "IT", 60000));
    employees.add(new Employee("Maria", "HR", 52000));
    employees.add(new Employee("John", "Finance", 70000));
    employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
    employees.add(new Employee("Lara", "IT", 62000));
    employees.add(new Employee("Sam", "HR", 48000));
    employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
    
    
    System.out.println("=== Unique Employees ===");
    
    Set<String> names = new HashSet<String>();
    List<Employee> cleaned = new ArrayList<Employee>();
    
	    for (Employee e : employees) {
	        if (names.contains(e.getName()) == false) {
	        	
	            names.add(e.getName());
	            cleaned.add(e);
	        }
	    }
	    for (Employee e : cleaned) {
	        System.out.println(e.toString());
	    }
    
	    
    System.out.println();
    System.out.println("=== Employees by Department ===");
    
    Map<String, List<Employee>> byDept = new HashMap<String, List<Employee>>();
      
	    for (Employee e : employees) {
	        String dept = e.getDepartment();
	        if (byDept.containsKey(dept)) {
	            byDept.get(dept).add(e);
	        } 
	        else {
	            List<Employee> list = new ArrayList<Employee>();
	            list.add(e);
	            byDept.put(dept, list);
	        }
	    }
	    for (String dept : byDept.keySet()) {
	    	
	        System.out.println(dept + ":");
	        List<Employee> list = byDept.get(dept);
	        
	        for (Employee e : list) {
	            System.out.println("  - " + e);
	        }
	    }
	    
    System.out.println();
    System.out.println("=== Highest paid by Department ===");
    
	    for (String dept : byDept.keySet()) {
	    	
	        List<Employee> list = byDept.get(dept);
	        Employee highest = list.get(0);
	        
	        for (Employee e : list) {
	            if (e.getSalary() > highest.getSalary()) {
	                highest = e;
	            }
	        }
	        
	        System.out.println(dept + ": " + highest);
	    }
	
	    
	 System.out.println();
     System.out.println("=== Employees Sorted by Salary (desc) ===");
        
     List<Employee> sorted = new ArrayList<Employee>(employees);
     Collections.sort(sorted);
     
        for (Employee e : sorted) {
            System.out.println(e.toString());
        }
    
	 System.out.println();
     System.out.println("=== Unique salaries (Sorted) ===");
     
     Set<Double> uniqueSalaries = new TreeSet<Double>();
     
	     for (Employee e : employees) {
	         uniqueSalaries.add(e.getSalary());
	     }
	     for (Double s : uniqueSalaries) {
	         System.out.println(s);
	     }
	    
	    
	    
	}
    
	
	
}
