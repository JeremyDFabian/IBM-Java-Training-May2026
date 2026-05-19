package DayTwo;

import java.util.ArrayList;

public class Library {
	
    ArrayList<Book> books = new ArrayList<Book>();

    public void addBook(Book b) {
    	
        books.add(b);
    }

    public void showAllBooks() {
    	
        for (int i = 0; i < books.size(); i++) {
            books.get(i).getInfo();
        }
    }

    public void borrowBook(String title) {
        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getTitle().toLowerCase().equals(title.toLowerCase())) {
                books.get(i).borrowBook();
            }

        }
    }

    public void returnBook(String title) {
        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getTitle().toLowerCase().equals(title.toLowerCase())) {
                books.get(i).returnBook();
            }

        }
    }
}