
package org.example.Controller;
import com.google.gson.Gson;
import org.example.Repository.UsersRepository;
import spark.Request;
import spark.Response;
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
    }


}
