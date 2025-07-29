package org.example.Repository;

import com.mysql.cj.protocol.Resultset;
import org.example.Model.LibraryContext;
import org.example.Model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    //Metodo para obtener toda la lista de usuarios

    public static List<Users> getAllUsers() throws SQLException {

        //Creo la lista donde se van a guardar los usuarios
        List<Users> usersList = new ArrayList<>();
        Users users;

        try (Connection conn = LibraryContext.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")
        ) {

            while (rs.next()) {
                users = new Users(
                        rs.getInt("id_user"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                usersList.add(users);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
}








