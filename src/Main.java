import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while(true) {
            System.out.println("\n====== Library Menu ======");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Register User");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Books by Title");
            System.out.println("7. Search UserId by Name");
            System.out.println("8. List All Books");
            System.out.println("9. List Books Alphabetically");
            System.out.println("10. Display User Info by Id");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int ch=sc.nextInt();
            switch(ch) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    int i=sc.nextInt();
                    library.removeBook(i);
                    break;
                case 3:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();
                    User newUser = new User(userId, name);
                    library.registerUser(newUser);
                    break;
                case 4:
                    System.out.print("Enter Book ID to issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    library.issueBook(issueBookId, issueUserId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnUserId = sc.nextInt();
                    library.returnBook(returnBookId, returnUserId);
                    break;

                case 6:
                    System.out.print("Enter title to search: ");
                    sc.nextLine();
                    String searchTitle = sc.nextLine();
                    library.searchBooksByTitle(searchTitle);
                    break;

                case 7:
                    sc.nextLine();
                    System.out.print("Enter User Name to search: ");
                    String searchName = sc.nextLine();
                    library.searchUserIdsByName(searchName);
                    break;

                case 8:
                    library.listBooks();
                    break;

                case 9:
                    library.listBooksAlphabetically();
                    break;

                case 10:
                    System.out.print("Enter User ID to view details: ");
                    int uid = sc.nextInt();
                    library.displayUserInfoById(uid);
                    break;

                case 11:
                    System.out.println("Exiting....");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}