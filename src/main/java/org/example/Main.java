package org.example;

import com.google.gson.Gson;
import org.example.Controller.BookController;


import org.example.Controller.LoanController;
import org.example.Controller.UsersController;
import org.example.Model.LibraryContext;

import java.sql.Connection;

import static spark.Spark.port;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //Prueba para ver si funciona la conexion con la base de datos.
    public static void main(String[] args) {

        Connection conn = LibraryContext.connect();
        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos");
        } else {
            System.out.println("No se pudo conectar a la base de datos");
        }

            port(4567); // Puerto

            BookController.setupRoutes();
            UsersController.setupRoutes();
            LoanController.setupRoutes();
        }



    }




