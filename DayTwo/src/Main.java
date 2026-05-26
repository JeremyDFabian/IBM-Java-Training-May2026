public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        Book b1 = new Book("Bourne Identity", "Robert Ludlum");
        Book b2 = new Book("Hunger Games", "Suzanne Collins");
        Book b3 = new Book("Godfather", "Mario Puzo");

        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        System.out.println("Books before borrowing:");
        lib.showAllBooks();

        lib.borrowBook("Hunger Games");
        lib.borrowBook("Hunger Games");

        lib.returnBook("Hunger Games");

        System.out.println("Books after:");
        lib.showAllBooks();
    }
}