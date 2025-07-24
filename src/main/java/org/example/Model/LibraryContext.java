package org.example.Model;

import java.sql.Connection;
import java.sql.DriverManager;


public class LibraryContext {

    public static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "Kopius2025!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}


