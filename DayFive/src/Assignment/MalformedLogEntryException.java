package Assignment;

public class MalformedLogEntryException extends Exception {

    public MalformedLogEntryException(String problem) {
        
        super(problem);
    }
}