public class Book {
    String title;
    String author;
    int numPages;
    Book() {};
    
    public Book(String t, String a, int p) {
        title = t;
        author = a;
        numPages = p;
    }
    public Book(String author, int Pages) {
        this.author = author;
        this.numPages = Pages;
    }
}