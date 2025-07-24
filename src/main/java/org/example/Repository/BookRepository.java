package org.example.Repository;

import com.mysql.cj.protocol.Resultset;
import org.example.Model.Book;
import org.example.Model.LibraryContext;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    //Metodo para obtener la lista de los libros.
    public static List<Book> getAllBooks() {
        //Creo la lista donde se van a guardar los libros.
        List<Book> bookList = new ArrayList<>();

        //Primero se hace el intento para conectarse a la base de datos.
        Book book;
        try (Connection conn = LibraryContext.connect()) {

            //Verifico si falla la conexion.
            if (conn == null) {
                System.err.println("Connection failed");
                return bookList;
            }

            //Si la conexion esta bien ejecuta la query.
            try
                    (Statement stmt = conn.createStatement(); //Desde la conexion se crea un Statement para poder ejecutar la consulta.
                     ResultSet rs = stmt.executeQuery("SELECT * FROM libro")) {
                while (rs.next()) {
                    book = new Book(
                            rs.getInt("id_book"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getInt("publication_year"),
                            rs.getString("author_name")
                    );
                    bookList.add(book); //Agrega el libro a la lista.
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

        //Metodo para obtener libro por id

        //Metodo para agregar un libro

        public static void createbook(Book book) {

            try (Connection conn = LibraryContext.connect()) {

                //Verifico si falla la conexion.
                if (conn == null) {
                    System.err.println("Connection failed");
                    return;
                }
            //Si no falla la conexion
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO libro (title, genre, publication_year, author_name) VALUES (?,?,?,?)")){
                 stmt.setString(1, book.getTitle());
                 stmt.setString(2,book.getGenre());
                 stmt.setInt(3,book.getPublication_year());
                 stmt.setString(4,book.getAuthor_name());

                 stmt.executeUpdate();

                 }

             } catch (SQLException e) {
                 e.printStackTrace();
             }
        }

        //Metodo para editar un libro

        public static void updatebook(Book book) {
            try (Connection conn = LibraryContext.connect()) {

                //Verifico si falla la conexion.
                if (conn == null) {
                    System.err.println("Connection failed");
                    return;
                }

                try (PreparedStatement stmt = conn.prepareStatement("UPDATE libro SET title = ?, genre = ?, publication_year = ?, author_name = ? WHERE id_book = ?")) {
                    stmt.setString(1, book.getTitle());
                    stmt.setString(2, book.getGenre());
                    stmt.setInt(3, book.getPublication_year());
                    stmt.setString(4, book.getAuthor_name());
                    stmt.setInt(5, book.getId_book());

                    stmt.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Metodo para eliminar un libro

        public static void deletebook(int idBook) {

            try (Connection conn = LibraryContext.connect()) {

                //Verifico si falla la conexion.
                if (conn == null) {
                    System.err.println("Connection failed");
                    return;
                }

                //Si no falla la conexion...
                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM libro where id=?")) {
                    stmt.setInt(1,idBook);

                    int rowsAffected = stmt.executeUpdate(); //ejecuta la consulta delete.
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
