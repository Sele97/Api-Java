package org.example.Repository;

import org.example.Model.Book;
import org.example.Model.LibraryContext;
import org.example.Model.Loan;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    //Metodo para obtener la lista de los prestamos.
    public static List <Loan> getAllLoans() throws SQLException {
        List<Loan> loanList = new ArrayList<>();
        Loan loan;

        try (Connection conn = LibraryContext.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM loan")) {

            while (rs.next()) { // rs.next mueve el cursor a la siguiente fila
                loan = new Loan(
                        rs.getInt("id_loan"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getInt("loan_date"),
                        rs.getInt("return_date")
                );
                loanList.add(loan);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loanList;

    }

    //Metodo para obtener prestamo por id.
    public static Loan getLoanByID (int id_loan){
        Loan loan = null;

        try {
            Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM loan WHERE user_id = ?");
            stmt.setInt(1, id_loan);
            ResultSet rs = stmt.executeQuery();




        }catch (SQLException e){
            e.printStackTrace();
        }

        return loan;




    }



}
