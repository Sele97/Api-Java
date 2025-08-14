
package org.example.Controller;
import com.google.gson.Gson;
import org.example.Repository.UsersRepository;
import spark.Request;
import spark.Response;
import org.example.Model.Users;
import static java.lang.reflect.Array.set;
import static spark.Spark.*;
import static spark.route.HttpMethod.*;
import static spark.Spark.get;
import static spark.route.HttpMethod.get;

public class UsersController {

    public static <response, request> void setupRoutes() {
        Gson gson = new Gson();

        //obtener todos los usuarios
        get("/users", (Request req, Response res) -> {
            res.type("application/json");
            return UsersRepository.getAllUsers();
        }, gson::toJson);

        //obtener usuario por id
        get("/users/:id_user", (Request req, Response res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_user"));
            Users foundUser = UsersRepository.getUserById(id);

            if (foundUser != null) {
                return foundUser;
            } else {
                return "User not found";
            }
        }, gson::toJson);

        //crear un usuario
        post("/users", (Request req, Response res) -> {
            res.type("application/json");
            Users newUser = gson.fromJson(req.body(), Users.class);
            UsersRepository.createUser(newUser);
            return "User created with exit!";
        }, gson::toJson);

        //editar un usuario
        put("/users/:id_user", (Request req, Response res) -> {
            res.type("application/json");

            Users user = gson.fromJson(req.body(), Users.class);
            user.setId_user(Integer.parseInt(req.params(":id_user")));

            UsersRepository.updateUser(user);
            return "Updated user";
        }, gson::toJson);

        //eliminar un usuario
        delete("/users/:id_user", (Request req, Response res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_user"));
            UsersRepository.deleteUser(id);
            return "Deleted user with ID " + id;
        });

    }

}
