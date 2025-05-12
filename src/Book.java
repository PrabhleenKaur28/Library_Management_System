public class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    public Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    //getters
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean isIssued(){
        return isIssued;
    }

    //setters
    public void setIssued(boolean issued){
        isIssued = issued;
    }
}
