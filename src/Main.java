public class Main {
    public static void main(String[] args) {
        // 1. Creamos el gestor de conexiones
        DBConnection dbConnection = new DBConnection();

        // 2. Creamos el repositorio pasando la conexión
        ConcreteRepositoryBook repository = new ConcreteRepositoryBook(dbConnection);

        // 3. Creamos el servicio, inyectándole el repositorio
        BookService service = new BookService(repository);

        // 4. Arrancamos el menú interactivo
        service.ShowMenu();
    }
}
