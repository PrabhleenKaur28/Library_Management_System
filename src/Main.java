import java.util.*;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book b1=new Book(1, "Harry Potter and the Philosopher's Stone", "J.K. Rowling");
        Book b2=new Book(2, "To Kill a Mockingbird", "Harper Lee");
        Book b3=new Book(3, "Harry Potter and the Chamber of Secrets", "J.K. Rowling");
        Book b4=new Book(4, "Atomic Habits", "James Clear");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.listBooks();

        User u1=new User(1,"Alice");
        User u2=new User(2,"Bob");
        library.registerUser(u1);
        library.registerUser(u2);

        library.searchBooksByTitle("Harry Potter");

        library.issueBook(1,1);
        library.issueBook(1,2);
        library.returnBook(1,1);
        library.issueBook(1,2);

        library.removeBook(4);
        library.listBooks();

    }
}