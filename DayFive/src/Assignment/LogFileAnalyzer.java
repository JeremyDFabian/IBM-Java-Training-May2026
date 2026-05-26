package Assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogFileAnalyzer {

    public static void main(String[] args) {

        String logFileLocation = "src/Assignment/server.log";
        String reportFileLocation = "src/Assignment/summary.txt";

        Map<String, Integer> numberOfLogsPerLevel = new HashMap<String, Integer>();
        List<String> savedErrorMessages = new ArrayList<String>();

        numberOfLogsPerLevel.put("INFO", 0);
        numberOfLogsPerLevel.put("WARN", 0);
        numberOfLogsPerLevel.put("ERROR", 0);

        int validLogEntries = 0;

        LocalDateTime firstLogTime = null;
        LocalDateTime lastLogTime = null;

        DateTimeFormatter logDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            BufferedReader logReader = new BufferedReader(new FileReader(logFileLocation));

            String logLine;

            while ((logLine = logReader.readLine()) != null) {

                try {
                    checkLogLine(logLine);

                    int closingBracketPosition = logLine.indexOf("]");
                    int colonPosition = logLine.indexOf(":", closingBracketPosition);
                
                    String datePart = logLine.substring(1, closingBracketPosition);
                    String levelPart = logLine.substring(closingBracketPosition + 2, colonPosition);
                    String messagePart = logLine.substring(colonPosition + 2);

                    LocalDateTime logTime = LocalDateTime.parse(datePart, logDateFormat);

                    int oldLevelCount = numberOfLogsPerLevel.get(levelPart);
                    numberOfLogsPerLevel.put(levelPart, oldLevelCount + 1);

                    if (levelPart.equals("ERROR")) {
                        savedErrorMessages.add(messagePart);
                    }

                    if (firstLogTime == null || logTime.isBefore(firstLogTime)) {
                        firstLogTime = logTime;
                    }

                    if (lastLogTime == null || logTime.isAfter(lastLogTime)) {
                        lastLogTime = logTime;
                    }

                    validLogEntries++;

                } catch (MalformedLogEntryException e) {
                    System.out.println("Invalid line skipped: " + logLine);
                    System.out.println("Reason: " + e.getMessage());
                }
            }

            logReader.close();

            createSummaryReport(reportFileLocation, validLogEntries, numberOfLogsPerLevel, savedErrorMessages,
                    firstLogTime, lastLogTime, logDateFormat);

            System.out.println("Summary report created successfully.");

        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + logFileLocation);
            System.out.println("Make sure server.log is inside src/DayFiveLogFileAnalyzer folder.");

        } catch (IOException e) {

            System.out.println("Error while reading the log file.");
        }
    }

    public static void checkLogLine(String logLine) throws MalformedLogEntryException {

        if (!logLine.startsWith("[") || !logLine.contains("]")) {
            throw new MalformedLogEntryException("Missing timestamp bracket.");
        }

        if (!logLine.contains("INFO") && !logLine.contains("WARN") && !logLine.contains("ERROR")) {
            throw new MalformedLogEntryException("Invalid log level.");
        }

        int closingBracketPosition = logLine.indexOf("]");
        int colonPosition = logLine.indexOf(":", closingBracketPosition);

        if (colonPosition == -1) {
            throw new MalformedLogEntryException("Missing colon after log level.");
        }
    }

    public static void createSummaryReport(String reportFileLocation, int validLogEntries, Map<String, Integer> numberOfLogsPerLevel,
            List<String> savedErrorMessages, LocalDateTime firstLogTime, LocalDateTime lastLogTime, DateTimeFormatter logDateFormat) {

        try {
            BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFileLocation));

            reportWriter.write("Log Summary Report");
            reportWriter.newLine();
            reportWriter.write("------------------");
            reportWriter.newLine();

            reportWriter.write("Total Entries: " + validLogEntries);
            reportWriter.newLine();

            reportWriter.write("INFO: " + numberOfLogsPerLevel.get("INFO"));
            reportWriter.newLine();

            reportWriter.write("WARN: " + numberOfLogsPerLevel.get("WARN"));
            reportWriter.newLine();

            reportWriter.write("ERROR: " + numberOfLogsPerLevel.get("ERROR"));
            reportWriter.newLine();

            reportWriter.newLine();

            reportWriter.write("Error Messages:");
            reportWriter.newLine();

            for (int i = 0; i < savedErrorMessages.size(); i++) {
                reportWriter.write("- " + savedErrorMessages.get(i));
                reportWriter.newLine();
            }

            reportWriter.newLine();

            if (firstLogTime != null && lastLogTime != null) {
                reportWriter.write("Earliest Timestamp: " + firstLogTime.format(logDateFormat));
                reportWriter.newLine();

                reportWriter.write("Latest Timestamp: " + lastLogTime.format(logDateFormat));
                reportWriter.newLine();
            } else {
                reportWriter.write("No valid timestamps found.");
                reportWriter.newLine();
            }

            reportWriter.close();

        } catch (IOException e) {
            System.out.println("Error while writing the summary file.");
        }
    }
}