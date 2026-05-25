package DayFiveLogFileAnalyzer;

public class MalformedLogEntryException extends Exception {

    public MalformedLogEntryException(String problem) {
        
        super(problem);
    }
}