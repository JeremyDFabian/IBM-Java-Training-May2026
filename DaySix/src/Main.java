import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static Connection connectToDatabase() {

        Connection studentDatabaseConnection = null;

        try {
            String studentDatabaseUrl = "jdbc:postgresql://localhost:5432/postgres";
            String studentDatabaseUsername = "postgres";
            String studentDatabasePassword = "123";

            studentDatabaseConnection = DriverManager.getConnection(
                    studentDatabaseUrl,
                    studentDatabaseUsername,
                    studentDatabasePassword
            );

        } catch (Exception databaseError) {
            System.out.println("Database connection failed.");
            System.out.println(databaseError.getMessage());
        }

        return studentDatabaseConnection;
    }

    public static void main(String[] args) {

        Scanner studentMenuInput = new Scanner(System.in);
        char studentMenuChoice;

        do {
            
            System.out.println("=== MENU ===");
            System.out.println("[A] Add Student");
            System.out.println("[V] View Students");
            System.out.println("[U] Update Student Password");
            System.out.println("[D] Delete Student");
            System.out.println("[Q] Quit");
            System.out.print("Enter your choice: ");

            studentMenuChoice = studentMenuInput.next().charAt(0);
            studentMenuInput.nextLine();

            switch (Character.toUpperCase(studentMenuChoice)) {

                case 'A':
                    AddStudent.addStudentRecord(studentMenuInput);
                    break;

                case 'V':
                    ViewStudent.viewStudentRecords();
                    break;

                case 'U':
                    UpdateStudentPassword.updatePasswordRecord(studentMenuInput);
                    break;

                case 'D':
                    DeleteStudent.deleteStudentRecord(studentMenuInput);
                    break;

                case 'Q':
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();

        } while (Character.toUpperCase(studentMenuChoice) != 'Q');

        studentMenuInput.close();
    }
}