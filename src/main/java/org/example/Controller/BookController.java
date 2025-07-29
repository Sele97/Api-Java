
package org.example.Controller;
import static spark.Spark.*;
import org.example.Model.Users;
import com.google.gson.Gson;
import org.example.Model.Book;
import org.example.Repository.BookRepository;
import spark.Request;
import spark.Response;

import java.util.List;

public class BookController {

    public static <response, request> void setupRoutes() {
        Gson gson = new Gson();

        //obtener todos los libros
        get("/books", (req, res) -> {
            res.type("application/json");
            return BookRepository.getAllBooks();
        }, gson::toJson); //convierte el mensaje a JSON

        //obtener libro por id
        get("/books/:id_book",(Request req, Response res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_book")); //se obtiene el ID de los parametros de la URL
            Book foundBook = BookRepository.getBookById(id); //se busca el libro en el repositorio

            if (foundBook!= null) {
                return foundBook; //si se encontro el libro lo devuelve
            } else {
                return "Book not found";
            }
        }, gson::toJson);

        //agregar un nuevo libro
        post("/books", (req, res) -> {
            res.type("application/json");
            Book newBook = gson.fromJson(req.body(), Book.class);
            BookRepository.createBook(newBook);
            return "Book created with exit!";
        });

        //editar un libro existente
        put("/books/:id_book", (req, res) -> {
            res.type("application/json");

            Book book = gson.fromJson(req.body(), Book.class);
            book.setId_book(Integer.parseInt(req.params(":id_book")));

            BookRepository.updateBook(book); //Se actualiza la base de datos
            return "Updated book";
        });

        //borrar un libro

        delete("/books/:id_book",(req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_book"));
            BookRepository.deleteBook(id);
            return "Deleted book with ID " + id;

        });

    }

    }










