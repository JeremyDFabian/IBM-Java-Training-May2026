import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddStudent {

    public static void addStudentRecord(Scanner studentMenuInput) {

        Connection studentDatabaseConnection = Main.connectToDatabase();

        if (studentDatabaseConnection == null) {

            System.out.println("Cannot add student because database is not connected.");
            return;
        }

        try {

            System.out.print("Enter student email: ");
            String studentEmail = studentMenuInput.nextLine();

            System.out.print("Enter student password: ");
            String studentPassword = studentMenuInput.nextLine();

            System.out.print("Enter student first name: ");
            String studentFirstName = studentMenuInput.nextLine();

            System.out.print("Enter student last name: ");
            String studentLastName = studentMenuInput.nextLine();

            String addStudentQuery = "INSERT INTO public.student " + "(email, password, firstname, lastname) " + "VALUES (?, ?, ?, ?)";

            PreparedStatement addStudentStatement = studentDatabaseConnection.prepareStatement(addStudentQuery);

            addStudentStatement.setString(1, studentEmail);
            addStudentStatement.setString(2, studentPassword);
            addStudentStatement.setString(3, studentFirstName);
            addStudentStatement.setString(4, studentLastName);

            addStudentStatement.executeUpdate();

            System.out.println("Student added successfully.");

            addStudentStatement.close();
            studentDatabaseConnection.close();

        } catch (Exception addStudentError) {

            System.out.println("Error adding student.");
            System.out.println(addStudentError.getMessage());
        }
    }
}