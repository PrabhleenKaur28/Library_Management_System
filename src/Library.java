import java.util.*;

public class Library {
    HashMap<Integer, Book> booksById;
    HashMap<String, List<Book>> booksByTitle;
    Hashtable<Integer, User> users;
    Stack<String> history;

    public Library() {
        booksById = new HashMap<>();
        booksByTitle = new HashMap<>();
        users = new Hashtable<>();
        history = new Stack<>();
    }

    public void addBook(Book book) {
        booksById.put(book.getId(), book);
        booksByTitle.putIfAbsent(book.getTitle(), new ArrayList<>());
        booksByTitle.get(book.getTitle()).add(book);
        history.push("Added Book: " + book.getId());
        System.out.println("Book added successfully.");
    }

    public void removeBook(int bookId) {
        if (booksById.containsKey(bookId)) {
            Book book = booksById.remove(bookId);
            List<Book> booksWithTitle = booksByTitle.get(book.getTitle());
            if (booksWithTitle != null) {
                booksWithTitle.remove(book);
                if (booksWithTitle.isEmpty()) {
                    booksByTitle.remove(book.getTitle());
                }
            }
            history.push("Removed Book: " + bookId);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
        history.push("Registered User: " + user.getUserId());
        System.out.println("User registered successfully.");
    }

    public void issueBook(int bookId, int userId) {
        Book book = booksById.get(bookId);
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        if(book==null || book.isIssued()){
            System.out.println("Book not available");
            return;
        }
        book.setIssued(true);
        user.issueBook(book);
        history.push("Issued Book: " + bookId + " to User: "+userId);
        System.out.println("Book Issued successfully.");
    }

    public void returnBook(int bookId, int userId) {
        Book book = booksById.get(bookId);
        User user = users.get(userId);

        if(user==null || book==null || !book.isIssued()){
            System.out.println("Return failed: book not found, not issued, or user doesn't exist.");
            return;
        }
        book.setIssued(false);
        user.returnBook(book);
        history.push("Returned Book: " + bookId + " from User: "+userId);
        System.out.println("Book Returned successfully.");
    }

    public void searchBooksByTitle(String title) {
        List<Book> books = booksByTitle.get(title);
        if (books != null && !books.isEmpty()) {
            System.out.println("Books found with title \"" + title + "\":");
            for (Book b : books) {
                System.out.println(b.getTitle());
            }
        } else {
            System.out.println("No books found.");
        }
        System.out.println();
    }

    public void listBooks() {
        if (booksById.isEmpty()) {
            System.out.println("No books found in the Library.");
        }else{
            for (Book book : booksById.values()) {
                System.out.println(book.getTitle());
            }
        }
    }
}
