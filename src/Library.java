import java.util.*;

public class Library {
    private int bookId=1;
    HashMap<Integer, Book> booksById;
    Hashtable<Integer, User> users;
    Trie titleTrie = new Trie();

    public Library() {
        booksById = new HashMap<>();
        users = new Hashtable<>();
    }

    public void addBook(String title, String author) {
        Book book=new Book(bookId++,title,author);
        booksById.put(book.getId(), book);
        titleTrie.insert(book.getTitle(), book);
        System.out.println("Book added successfully.");
    }

    public void removeBook(int bookId) {
        if (booksById.containsKey(bookId)) {
            Book book = booksById.get(bookId);
            if (book.isIssued()) {
                System.out.println("Cannot remove a book that is currently issued.");
                return;
            }
            booksById.remove(bookId);
            titleTrie.delete(book.getTitle(), book);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }


    public void registerUser(User user) {
        if (users.containsKey(user.getUserId())) {
            System.out.println("User ID already exists.");
            return;
        }
        users.put(user.getUserId(), user);
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
        System.out.println("Book Returned successfully.");
    }

    public void searchBooksByTitle(String title) {
        List<Book> books = titleTrie.searchByPrefix(title);
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
                System.out.println(book.getId()+". "+book.getTitle());
            }
        }
    }

    public void listBooksAlphabetically() {
        List<Book> sortedBooks = sortBooksAlphabetically();
        if (sortedBooks.isEmpty()) {
            System.out.println("No books found in the Library.");
        } else {
            for (Book book : sortedBooks) {
                System.out.println(book.getTitle());
            }
        }
    }
    private List<Book> sortBooksAlphabetically() {
        List<Book> books = new ArrayList<>(booksById.values());
        return mergeSort(books);
    }
    private List<Book> mergeSort(List<Book> books) {
        if(books.size()<=1){
            return books;
        }
        int mid=books.size()/2;
        List<Book> left=mergeSort(books.subList(0, mid));
        List<Book> right=mergeSort(books.subList(mid, books.size()));
        return merge(left,right);
    }
    private List<Book> merge(List<Book> left, List<Book> right) {
        List<Book> merged = new ArrayList<>();
        int i=0,j=0;
        while(i<left.size() && j<right.size()){
            if(left.get(i).getTitle().compareToIgnoreCase(right.get(j).getTitle()) <= 0){
                merged.add(left.get(i++));
            }else{
                merged.add(right.get(j++));
            }
        }
        while(i<left.size()){
            merged.add(left.get(i++));
        }
        while(j<right.size()){
            merged.add(right.get(j++));
        }
        return merged;
    }

    public void searchUserIdsByName(String name) {
        boolean found = false;
        System.out.println("Searching for users with name: \"" + name + "\"");
        for (User user : users.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No user found with name \"" + name + "\".");
        }
    }

    public void displayUserInfoById(int userId) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        System.out.println("===== User Details =====");
        System.out.println("User ID   : " + user.getUserId());
        System.out.println("User Name : " + user.getName());

        List<Book> issued = user.getIssuedBooks();
        if (issued.isEmpty()) {
            System.out.println("Issued Books: None");
        } else {
            System.out.println("Issued Books:");
            for (Book book : issued) {
                System.out.println("  - [" + book.getId() + "] " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }


}
