package org.example.Repository;
import org.example.Model.Book;

import org.example.Model.LibraryContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    //Metodo para obtener la lista de los libros.
    public static List<Book> getAllBooks() throws SQLException {
        //Creo la lista donde se van a guardar los libros.
        List<Book> bookList = new ArrayList<>();
        Book book;

        try (Connection conn = LibraryContext.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM book")) {

            while (rs.next()) {
                book = new Book(
                        rs.getInt("id_book"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("publication_year"),
                        rs.getString("author_name")
                );
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    //Metodo para obtener libro por id

    public static Book getBookbyId(int id_book) {
        Book book = null;

        try {
            Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book WHERE id_book = ?");
            stmt.setInt(1, id_book);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                book = new Book(
                        rs.getInt("id_book"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("publication_year"),
                        rs.getString("author_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //Metodo para agregar un libro

    public static void createbook(Book book) {

        try (Connection conn = LibraryContext.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO book (title, genre, publication_year, author_name) VALUES (?,?,?,?)")) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getGenre());
            stmt.setInt(3, book.getPublication_year());
            stmt.setString(4, book.getAuthor_name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para editar un libro

    public static void updatebook(Book updatebook) {
        try (Connection conn = LibraryContext.connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE book SET title = ?, genre = ?, publication_year = ?, author_name = ? WHERE id_book = ?")) {

            stmt.setString(1, updatebook.getTitle());
            stmt.setString(2, updatebook.getGenre());
            stmt.setInt(3, updatebook.getPublication_year());
            stmt.setString(4, updatebook.getAuthor_name());
            stmt.setInt(5, updatebook.getId_book());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para eliminar un libro por id

    public static void deletebook(int id_book) throws SQLException {

        try (Connection conn = LibraryContext.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM book where id=?")) {
            stmt.setInt(1, id_book);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún libro con ese ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

