package org.example.Model;
import java.util.Date;

public class Loan {
    private int id_loan;
    private int user_id;
    private int book_id;
    private Date loan_date;
    private Date return_date;

    public Loan(int id_loan, int user_id, int book_id, Date loan_date, Date return_date) {
        this.id_loan = id_loan;
        this.user_id = user_id;
        this.book_id= book_id;
        this.loan_date = loan_date;
        this.return_date= return_date;
    }

    public void setId_loan(int id_loan)
    {
        this.id_loan = id_loan;
    }

    public int getId_loan()
    {
        return id_loan;
    }

    public int setUser_id(int user_id)
    {   this.user_id = user_id;
        return user_id;
    }

    public int getId_user(){
        return user_id;
    }

    public void setBook_id(int book_id)
    {
        this.book_id = book_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date)
    {
        this.loan_date = loan_date;
    }

    public Date getReturn_date()
    {
        return return_date;
    }

    public void setReturn_date(Date return_date)
    {
        this.return_date = return_date;
    }

}
