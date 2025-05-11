package Product;

public class Book {
    private int idBook;
    private String titleOfTheBook;
    private String author;
    private String publisher;
    private double price;

    public Book(int idBook, String titleOfTheBook, String author, String publisher, double price) {
        this.idBook = idBook;
        this.titleOfTheBook = titleOfTheBook;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitleOfTheBook() {
        return titleOfTheBook;
    }

    public void setTitleOfTheBook(String titleOfTheBook) {
        this.titleOfTheBook = titleOfTheBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void printBook() {
        System.out.println("ID: " + getIdBook());
        System.out.println("Título: " + getTitleOfTheBook());
        System.out.println("Autor: " + getAuthor());
        System.out.println("Editorial: " + getPublisher());
        System.out.println("Precio: $" + getPrice());
        System.out.println("----------------------------");

    }
}
