package org.example.Repository;
import org.example.Model.LibraryContext;
import org.example.Model.Loan;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
                        rs.getDate("loan_date"),
                        rs.getDate("return_date")
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
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM loan WHERE id_loan= ?");
            stmt.setInt(1, id_loan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loan = new Loan(
                        rs.getInt("id_loan"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("loan_date"),
                        rs.getDate("return_date")
                );
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return loan;

    }

    //Metodo para crear un prestamo
    public static void createLoan(Loan loan){
        try
            (Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO loan (id_loan, user_id, book_id, loan_date, return_date) VALUES (?,?,?,?,?)")) {

            stmt.setInt(1, loan.getId_loan());
            stmt.setInt(2, loan.getId_user());
            stmt.setInt(3, loan.getBook_id());
            stmt.setDate(4, new java.sql.Date(loan.getLoan_date().getTime()));
            stmt.setDate(5, new java.sql.Date(loan.getReturn_date().getTime()));

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para editar un prestamo
    public static void updateLoan(Loan updateLoan) {
        try  {
            Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE loan SET book_id= ?, loan_date = ?, return_date = ?, user_id = ? WHERE id_loan = ?");

            stmt.setInt(1, updateLoan.getBook_id());
            stmt.setDate(2, new java.sql.Date(updateLoan.getLoan_date().getTime()));
            stmt.setDate(3, new java.sql.Date(updateLoan.getReturn_date().getTime()));
            stmt.setInt(4, updateLoan.getId_user());
            stmt.setInt(5, updateLoan.getId_loan());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 }







