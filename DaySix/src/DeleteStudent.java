import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteStudent {

    public static void deleteStudentRecord(Scanner studentMenuInput) {

        Connection studentDatabaseConnection = Main.connectToDatabase();

        if (studentDatabaseConnection == null) {

            System.out.println("Cannot delete student because database is not connected.");
            return;
        }

        try {

            System.out.print("Enter student ID to delete: ");

            int studentIdNumber = studentMenuInput.nextInt();
            studentMenuInput.nextLine();

            String deleteStudentQuery = "DELETE FROM public.student WHERE studentid = ?";

            PreparedStatement deleteStudentStatement = studentDatabaseConnection.prepareStatement(deleteStudentQuery);

            deleteStudentStatement.setInt(1, studentIdNumber);

            int deletedStudentRows = deleteStudentStatement.executeUpdate();

            if (deletedStudentRows > 0) {

                System.out.println("Student deleted successfully.");
            } else {

                System.out.println("Student ID not found.");
            }

            deleteStudentStatement.close();
            studentDatabaseConnection.close();

        } catch (Exception deleteStudentError) {

            System.out.println("Error deleting student.");
            System.out.println(deleteStudentError.getMessage());
        }
    }
}