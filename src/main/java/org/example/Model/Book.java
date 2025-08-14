package org.example.Model;
import java.time.LocalDateTime;

public class Book {
        private int id_book;
        private String title;
        private String genre;
        private int publication_year;
        private String author_name;

    public Book(int id_book, String title, String genre, int publication_year, String author_name) {
        this.id_book = id_book;
        this.title = title;
        this.genre = genre;
        this.publication_year = publication_year;
        this.author_name = author_name;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_book(){
        return id_book;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getGenre(){
        return genre;
    }

    public void setPublication_year(int publicationYear){
        this.publication_year = publicationYear;
    }

    public int getPublication_year(){
        return publication_year;
    }

    public void setAuthor_name(String author_name){
        this.author_name = author_name;
    }

    public String getAuthor_name(){
        return author_name;
    }

}
