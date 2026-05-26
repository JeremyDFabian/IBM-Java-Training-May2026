import java.io.*;

public class StudentFile {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/DayFive/student.csv"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src/DayFive/student.json"))) {

            bw.write("[");
            bw.newLine();

            String line;

            boolean firstRow = true;     
            boolean firstObject = true;  

            while ((line = br.readLine()) != null) {

                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                line = line.replace("\"", "");

                String[] words = line.split(",");

                if (!firstObject) {
                    bw.write(",");      
                    bw.newLine();
                }

                firstObject = false;

                bw.write("  {");
                bw.newLine();
                bw.write("   \"id\": \"" + words[0].trim() + "\",");
                bw.newLine();
                bw.write("   \"name\": \"" + words[1].trim() + "\",");
                bw.newLine();
                bw.write("   \"course\": \"" + words[2].trim() + "\"");
                bw.newLine();
                bw.write("  }");         
            }

            bw.newLine();
            bw.write("]");
            bw.newLine();

            System.out.println("File written successfully.");
        } 
        catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}