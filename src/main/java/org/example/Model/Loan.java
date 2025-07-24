package org.example.Model;

public class Loan {

    private int id_loan;
    private int user_id;
    private int book_id;
    private int loan_date;
    private int return_date;

    public void setId_loan(int id_loan){
        this.id_loan = id_loan;
    }

    public int getId_loan(){
        return id_loan;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setBook_id(int book_id){
        this.book_id = book_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void getLoan_date(int loan_date){
        this.loan_date = loan_date;
    }

    public int setLoan_date(){
        return loan_date;
    }

    public void getReturn_date(int return_date){
        this.return_date = return_date;
    }

    public int setReturn_date(){
        return return_date;
    }

}
