package org.example.Controller;
import com.google.gson.Gson;
import org.example.Model.Loan;
import org.example.Repository.LoanRepository;
import org.example.Repository.UsersRepository;
import spark.Request;
import spark.Response;
import java.util.List;
import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.put;
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

        //crear un prestamo
        post("/loan", (Request req, Response res) -> {
            res.type("application/json");
            Loan newLoan = gson.fromJson(req.body(), Loan.class);
            LoanRepository.createLoan(newLoan);
            return "Loan created with exit!";
        }, gson::toJson);

        //editar un prestamo
        put("/loan/:id_loan", (Request req, Response res) -> {
            res.type("application/json");
            Loan loan = gson.fromJson(req.body(), Loan.class);
            loan.setId_loan(Integer.parseInt(req.params(":id_loan")));
            LoanRepository.updateLoan(loan);
            return "Loan updated successfully";
        }, gson::toJson);

        //eliminar un prestamo
        delete("/loans/:id_loan", (Request req, Response res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_loan"));
            LoanRepository.deleteLoan(id);
            return "Deleted loan with ID " + id;
        });






    }



}
















