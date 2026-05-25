package DayFive;
import java.io.*;

public class StudentFile {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("IBM-Java-Training-May2026/src/DayFive/student.csv"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("IBM-Java-Training-May2026/src/DayFive/student.json"))) {

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
				//String[] words = line.split(",");

                int firstComma = line.indexOf(",");
                int secondComma = line.indexOf(",", firstComma + 1);

                String id = line.substring(0, firstComma).trim();
                String name = line.substring(firstComma + 1, secondComma).trim();
                String course = line.substring(secondComma + 1).trim();

                if (!firstObject) {
                    bw.write(",");      
                    bw.newLine();
                }

                firstObject = false;

                bw.write("  {");
                bw.newLine();
                bw.write("   \"id\": \"" + words[0] + "\",");
                bw.newLine();
                bw.write("   \"name\": \"" + words[1] + "\",");
                bw.newLine();
                bw.write("   \"course\": \"" + words[2] + "\"");
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