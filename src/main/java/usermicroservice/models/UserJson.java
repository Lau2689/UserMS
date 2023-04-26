package usermicroservice.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;



public class UserJson {
    public static void main( String[] args ) throws JsonProcessingException {

        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);


        try (FileWriter file = new FileWriter("user.json")) {
            file.write(userJson);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
