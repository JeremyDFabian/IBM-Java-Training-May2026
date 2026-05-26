import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateStudentPassword {

    public static void updatePasswordRecord(Scanner studentMenuInput) {

        Connection studentDatabaseConnection = Main.connectToDatabase();

        if (studentDatabaseConnection == null) {
            System.out.println("Cannot update password because database is not connected.");
            return;
        }

        try {
            System.out.print("Enter student ID: ");
            int studentIdNumber = studentMenuInput.nextInt();
            studentMenuInput.nextLine();

            System.out.print("Enter new student password: ");
            String newStudentPassword = studentMenuInput.nextLine();

            String updatePasswordQuery = "UPDATE public.student " + "SET password = ?, dateupdated = CURRENT_TIMESTAMP " + "WHERE studentid = ?";

            PreparedStatement updatePasswordStatement = studentDatabaseConnection.prepareStatement(updatePasswordQuery);

            updatePasswordStatement.setString(1, newStudentPassword);
            updatePasswordStatement.setInt(2, studentIdNumber);

            int updatedStudentRows = updatePasswordStatement.executeUpdate();

            if (updatedStudentRows > 0) {
                System.out.println("Student password updated successfully.");
            } else {
                System.out.println("Student ID not found.");
            }

            updatePasswordStatement.close();
            studentDatabaseConnection.close();

        } catch (Exception updatePasswordError) { 
            System.out.println("Error updating student password.");
            System.out.println(updatePasswordError.getMessage());
        }
    }
}