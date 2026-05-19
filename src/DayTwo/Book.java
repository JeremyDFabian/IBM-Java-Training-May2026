package DayTwo;

public class Book {
	
    private String title;
    private String author;
    private boolean available;

    public Book(String t, String a) {
        title = t;
        author = a;
        available = true;
    }

    public String getTitle() {
        return title;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println(title + " borrowed.");
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println(title + " returned.");
    }

    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + available);
        System.out.println();
    }
}