import Product.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcreteRepositoryBook implements BookRepository {
    private final DBConnection dbConnection;

    public ConcreteRepositoryBook(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addBook(Book newBook) {
        String sql = "INSERT INTO books (id, title, author, publisher, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newBook.getIdBook());
            ps.setString(2, newBook.getTitleOfTheBook());
            ps.setString(3, newBook.getAuthor());
            ps.setString(4, newBook.getPublisher());
            ps.setDouble(5, newBook.getPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al agregar libro: " + e.getMessage());
        }
    }

    @Override
    public Book getBook(int idBook) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBook);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("publisher"),
                            rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener libro: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateBook(Book updatedBook) {
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, price = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedBook.getTitleOfTheBook());
            ps.setString(2, updatedBook.getAuthor());
            ps.setString(3, updatedBook.getPublisher());
            ps.setDouble(4, updatedBook.getPrice());
            ps.setInt(5, updatedBook.getIdBook());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(int idBook) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBook);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
        return list;
    }
}
