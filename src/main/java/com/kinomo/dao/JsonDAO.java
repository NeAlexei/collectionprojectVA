package com.kinomo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kinomo.model.User;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class JsonDAO implements DAO {

    private List<User> users;

    public JsonDAO() throws FileNotFoundException {
        if (users == null) {
            String user = "src/main/java/com/kinomo/users.json";
            initialize(user);
        }
    }

    @Override
    public void initialize(String userObject) throws FileNotFoundException {

        //convert the json string back to object
        Gson gson = new Gson();
        JsonReader string = new JsonReader(new FileReader(userObject));
        TypeToken type = new TypeToken<List<User>>() {};
        users = gson.fromJson(string,  type.getType());
        //System.out.println(users);
        //User jsonObject = gson.fromJson(string, User.class);
        //System.out.println(jsonObject);
    }

    @Override
    public User getById(String userId) {
       // User object = users.get(1);
        User a = null;
        for(User user: users) {
            if(user.getId().equals(userId)) {
                a = user;
            }
        }
       return a;
    }

    @Override
    public List<User> getAll() {

        for (User user : users) {
            System.out.println(user);
        }

        return users;
    }

    @Override
    public Map<String, List<User>> getUnique() {

        Map<String, List<User>> newMap = new HashMap<>();
        Set<String> userSet = new HashSet<>();

        for (User user : users) {
            userSet.add(user.getName());
        }

        for (String name : userSet) {
            List<User> userList = new ArrayList<>();

            for (User user : users) {
                if (name.equals(user.getName())) {
                    userList.add(user);
                }
            }

            newMap.put(name, userList);
        }

        return newMap;
    }

    /*@Override
    public Map<String, List<User>> getUnique() {

        Map<String, List<User>> newMap = new HashMap<>();

        for (User user : users) {

            List<User> myUsers = newMap.get(user.getName());

            if (myUsers == null) {
                myUsers = new ArrayList<>();
                newMap.put(user.getName(), myUsers);
            }
            myUsers.add(user);
        }
        return newMap;
    }*/
}
