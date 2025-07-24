package org.example.Model;

import java.sql.Connection;
import java.sql.DriverManager;


public class LibraryContext {

    public static Connection connect() {
        try {
            return DriverManager.getConnection(
                    "databaseConnection", "user", "pass");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}


