import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewStudent {

    public static void viewStudentRecords() {

        Connection studentDatabaseConnection = Main.connectToDatabase();

        if (studentDatabaseConnection == null) {
            System.out.println("Cannot view students because database is not connected.");
            return;
        }

        try {
            String viewStudentQuery = "SELECT * FROM public.student ORDER BY studentid";

            PreparedStatement viewStudentStatement =
                    studentDatabaseConnection.prepareStatement(viewStudentQuery);

            ResultSet studentRecordList = viewStudentStatement.executeQuery();

            System.out.println();
            System.out.println("STUDENT RECORDS");
            System.out.println("---------------------------------------------");

            while (studentRecordList.next()) {
                int studentId = studentRecordList.getInt("studentid");
                String studentEmail = studentRecordList.getString("email");
                String studentPassword = studentRecordList.getString("password");
                String studentFirstName = studentRecordList.getString("firstname");
                String studentLastName = studentRecordList.getString("lastname");

                System.out.println("Student ID: " + studentId);
                System.out.println("Email: " + studentEmail);
                System.out.println("Password: " + studentPassword);
                System.out.println("First Name: " + studentFirstName);
                System.out.println("Last Name: " + studentLastName);
                System.out.println("Date Added: " + studentRecordList.getTimestamp("dateadded"));
                System.out.println("Date Updated: " + studentRecordList.getTimestamp("dateupdated"));
                System.out.println("---------------------------------------------");
            }

            studentRecordList.close();
            viewStudentStatement.close();
            studentDatabaseConnection.close();

        } catch (Exception viewStudentError) {
            System.out.println("Error viewing student records.");
            System.out.println(viewStudentError.getMessage());
        }
    }
}