package org.example.Repository;
import com.mysql.cj.protocol.Resultset;
import org.eclipse.jetty.server.Authentication;
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

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

    //Metodo para obtener un usuario por id
    public static Users getUserById(int id_user) throws SQLException {
        Users user = null;

        try {
            Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id_user = ?");
            stmt.setInt(1, id_user);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new Users(
                        rs.getInt("id_user"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //Metodo para crear un usuario
    public static void createUser(Users user) {

        try {
            Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(id_user, first_name, last_name) VALUES (?,?,?)");

            stmt.setInt(1, user.getId_user());
            stmt.setString(2, user.getFirst_name());
            stmt.setString(3, user.getLast_name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para editar un usuario
    public static void updateUser(Users updateUser) {

        try {
            Connection conn = LibraryContext.connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET first_name= ?, last_name=? WHERE id_user = ?");

            stmt.setString(1, updateUser.getFirst_name());
            stmt.setString(2, updateUser.getLast_name());
            stmt.setInt(3, updateUser.getId_user());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para eliminar un usuario
    public static void deleteUser(int id_user) {
        try (Connection conn = LibraryContext.connect()) {
            // Primero eliminar loans asociados
            PreparedStatement deleteLoans = conn.prepareStatement("DELETE FROM loan WHERE user_id = ?");
            deleteLoans.setInt(1, id_user);
            deleteLoans.executeUpdate();

            // Despues eliminar usuario
            PreparedStatement deleteUser = conn.prepareStatement("DELETE FROM users WHERE id_user = ?");
            deleteUser.setInt(1, id_user);
            int rowsAffected = deleteUser.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User successfully removed");
            } else {
                System.out.println("No user was found with that ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}











