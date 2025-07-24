
package org.example.Controller;
import static spark.Spark.*;
import org.example.Model.Users;
import com.google.gson.Gson;
import org.example.Model.Book;
import org.example.Repository.BookRepository;
import java.util.List;

public class BookController {

    public static void setupRoutes() {
        Gson gson = new Gson();

        //obtener todos los libros
        get("/books", (req, res) -> {
            res.type("application/json");
            return BookRepository.getAllBooks();
        }, gson::toJson);

        //

        //agregar un nuevo libro
        post("/books", (req, res) -> {
            res.type("application/json");
            Book newBook = gson.fromJson(req.body(), Book.class);
            BookRepository.createbook(newBook);
            return "Book created with exit!";
        });

        //editar un libro existente
        put("/books/id", (req, res) -> {
            res.type("application/json");

            Book book = gson.fromJson(req.body(), Book.class);
            book.setId_book(Integer.parseInt(req.params(":id")));

            BookRepository.updatebook(book);
            return "Updated book";
        });

        //borrar un libro

        delete("/books/id",(req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            BookRepository.deletebook(id);
            return "Deleted book with ID " + id;

        });

    }

    }










