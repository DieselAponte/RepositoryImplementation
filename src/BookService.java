import Product.Book;
import java.util.Scanner;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;
    private final Scanner scanner;

    public BookService(ConcreteRepositoryBook bookRepository){
        this.bookRepository = bookRepository;
        this.scanner = new Scanner(System.in);
    }



    public void ShowMenu(){
        while(true){
            System.out.println("\n--- Biblioteca - Menú Principal ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Obtener libro por ID");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Listar todos los libros");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try{
                int option = Integer.parseInt(scanner.nextLine());
                switch(option){
                    case 1 -> addBookMenu();
                    case 2 -> getBookMenu();
                    case 3 -> updateBookMenu();
                    case 4 -> deleteBookMenu();
                    case 5 -> listAllBooks();
                    case 6 -> {
                        System.out.println("Saliendo del sistema . . . ");
                        return;
                    }
                    default -> System.out.println("Opcion no valida");
                }
            } catch (NumberFormatException e){
                System.out.println("Error, debe ingresar un numerovalido");
            } catch (Exception e){
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    private void addBookMenu(){
        try{
            System.out.println("ID del libro: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Titulo: ");
            String title = scanner.nextLine();
            System.out.println("Autor: ");
            String author = scanner.nextLine();
            System.out.println("Editorial: ");
            String publisher = scanner.nextLine();
            System.out.println("Precio: ");
            double price = Double.parseDouble(scanner.nextLine());

            Book newBook = new Book(id, title, author, publisher, price);
            bookRepository.addBook(newBook);
            System.out.println("Libro agregado exitosamente . . .");
        } catch (NumberFormatException e){
            System.out.println("Error, formato numérico invalido");
        }
    }

    private void getBookMenu(){
        System.out.println("ID del libro a buscar: ");
        try{
            int id = Integer.parseInt(scanner.nextLine());
            Book book = bookRepository.getBook(id);
            if(book != null){
                book.printBook();
            } else {
                System.out.println("Libro no encontrado");
            }
        } catch (NumberFormatException e){
            System.out.println("ID no valido");
        }
    }

    private void updateBookMenu(){
        try {
            System.out.print("ID del libro a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Book existing = bookRepository.getBook(id);
            if (existing == null) {
                System.out.println("Libro no encontrado.");
                return;
            }

            System.out.print("Nuevo título: ");
            String title = scanner.nextLine();

            System.out.print("Nuevo autor: ");
            String author = scanner.nextLine();

            System.out.print("Nueva editorial: ");
            String publisher = scanner.nextLine();

            System.out.print("Nuevo precio: ");
            double price = Double.parseDouble(scanner.nextLine());

            Book updated = new Book(id, title, author, publisher, price);
            bookRepository.updateBook(updated);
            System.out.println("Libro actualizado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido en los datos.");
        }
    }

    private void deleteBookMenu(){
        System.out.print("ID del libro a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            bookRepository.deleteBook(id);
            System.out.println("Libro eliminado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private void listAllBooks() {
        List<Book> books = bookRepository.getAllBooks();  // 1. Recuperar lista
        if (books.isEmpty()) {                            // 2. Verificar vacía
            System.out.println("No hay libros en la biblioteca.");
        } else {
            // 3. Recorrer e imprimir cada libro
            for (Book b : books) {
                b.printBook();
            }
        }
    }

}
