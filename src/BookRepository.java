import Product.Book;
import java.util.List;

public interface BookRepository {
    void addBook(Book newBook);
    Book getBook(int idBook);
    void updateBook(Book updatedBook);
    void deleteBook(int idBook);
    List<Book> getAllBooks();
}
