import java.util.*;

public class User {
    int userId;
    String name;
    List<Book> issuedBooks;

    public User(int userId, String name){
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    //getters

    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void issueBook(Book book){
        issuedBooks.add(book);
    }
    public void returnBook(Book book){
        issuedBooks.remove(book);
    }
}
