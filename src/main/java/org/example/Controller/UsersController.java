
package org.example.Controller;
import com.google.gson.Gson;
import org.example.Repository.UsersRepository;
import spark.Request;
import spark.Response;
import org.example.Model.Users;

import static spark.Spark.get;
import static spark.route.HttpMethod.get;

public class UsersController {

    public static <response, request> void setupRoutes() {
        Gson gson = new Gson();

        //obtener todos los usuarios
        get("/users", (Request req , Response res) -> {
            res.type("application/json");
            return UsersRepository.getAllUsers();
        }, gson::toJson);

        //obtener usuario por id
        get("/users/:id_user", (Request req, Response res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id_user"));
            Users foundUser = UsersRepository.getUserById(id);

            if (foundUser !=null) {
                return foundUser;
            }else{
                return "User not found";
                }
        }, gson::toJson);





    }



}
