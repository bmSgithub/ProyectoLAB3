package com.mygdx.game.Users;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.game.Exceptions.DuplicateIdException;
import com.mygdx.game.Utilities.Console;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private static final String JSON_FILE_PATH = "Resources/users.json";
    private static JsonReader jsonReader = new JsonReader();
    private static JsonValue jsonData;
    private List<User> userList;

    public UserManagement() {
        userList = load();
        User.updateLastAssignedId(userList);
    }

    public List<User> load() throws RuntimeException {
        FileHandle file = new FileHandle(JSON_FILE_PATH);
        userList = new ArrayList<>();

        try {
            if (file.exists()) {
                jsonReader = new JsonReader();
                jsonData = jsonReader.parse(file);

                for (JsonValue userValue : jsonData) {
                    User user = new User();
                    user.setId(userValue.getInt("id"));
                    user.setUsername(userValue.getString("username"));
                    user.setPoints(userValue.getInt("points"));
                    userList.add(user);
                }

                User.updateLastAssignedId(userList);
                User.generateNextId();
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    public void save(User user) throws DuplicateIdException {
        userList = load();
        try{
            if(!userList.contains(user)) {
                user.setId(User.generateNextId());
                userList.add(user);
                FileHandle file = new FileHandle(JSON_FILE_PATH);

                Json jsonWriter = new Json();
                jsonWriter.setOutputType(JsonWriter.OutputType.json);
                String json = jsonWriter.prettyPrint(userList);
                file.writeString(json, false);
            }else {
                throw new DuplicateIdException("User", user.getId());
            }
        }
        catch (DuplicateIdException e){
            Console.write(e.getMessage());
            throw e;
        }
    }
}
