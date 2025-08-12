package org.example.Controller;
import com.google.gson.Gson;
import org.example.Model.Book;
import org.example.Model.Loan;
import org.example.Repository.BookRepository;
import org.example.Repository.LoanRepository;
import spark.Request;
import spark.Response;
import org.example.Model.Loan;

import java.util.List;

import static java.lang.reflect.Array.set;
import static spark.Spark.*;
import static spark.route.HttpMethod.*;
import static spark.Spark.get;
import static spark.route.HttpMethod.get;

public class LoanController {

    public static <response, request> void setupRoutes() {
        Gson gson = new Gson();

        //obtener todos los prestamos
        get("/loans", (Request req, Response res) -> {
            res.type("application/json");
            List<Loan> loans = LoanRepository.getAllLoans();
            return gson.toJson(loans);
        });

        //obtener prestamo por id
        get("/loan/:id_loan", (Request req, Response res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_loan"));
            Loan foundLoan = LoanRepository.getLoanByID(id);

            if (foundLoan != null) {
                return foundLoan;
            } else {
                res.status(404);
                return "Loan not found";
            }
        }, gson::toJson); 
    }






    }








