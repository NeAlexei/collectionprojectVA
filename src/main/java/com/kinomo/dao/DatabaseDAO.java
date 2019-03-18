package com.kinomo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kinomo.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kinomo.model.Users;
import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.ConnectionString;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;


//implement later
public class DatabaseDAO implements DAO {
    private List<User> userList;
    private User userOne = null;
    private String user = "platform";
    private char[] password = new char[]{'S', 'B', 'U', 'h', 'X', '8', 'K', 'm', 'p', 'c', 'r', '7', 'T'};
    private String source = "stage-platform";
    private MongoCollection<Document> collection;
    String allString = null;

/*public DatabaseDAO() throws FileNotFoundException {
    if (userList == null) {
        initialize("users");
    }
}*/

    @Override
    public void initialize(String userObject)throws FileNotFoundException {
        //MongoCredential credential = MongoCredential.createCredential(user, source, password);
        ConnectionString connectionString = new ConnectionString("mongodb://platform:SBUhX8Kmpcr7T@10.10.0.27:27017,10.10.0.26:27017,10.10.0.28:27017");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(source);
        //collection = database.getCollection(userObject, User.class);
        collection = database.getCollection(userObject);
        System.out.println(collection.countDocuments());

        //------------------------********************************

    Gson gson = new Gson();
    //BufferedReader string = new BufferedReader(new FileReader("src/main/java/com/kinomo/users.json"));
    String string = collection.find().skip(0).limit(10).toString();

        for (Document document : collection.find().skip(0).limit(10)) {
           allString = allString + document.toJson();
      }
        System.out.println(allString);
//    TypeToken type = new TypeToken<List<User>>() {};
//    userList = gson.fromJson(allString, type.getType());
        TypeToken type = new TypeToken<MongoCollection<Document>>() {};
        userList = gson.fromJson(allString, type.getType());

}

    @Override
    public User getById(String userId) {

//        Document document = collection.find(eq("_id", userId)).first();
        //String a = collection.find(eq("_id", userId)).first().toJson();

//        System.out.println(document.toJson());
//        userList.get(userOne).equals(userId);
  //      return userOne;

        for(User user: userList) {
            if(user.getId().equals(userId)) {
               userOne = user;
            }
        }
        return userOne;

    }

    @Override
    public List<User> getAll() {

        for (Document document : collection.find().skip(0).limit(10)) {
            System.out.println(document.toJson());
        }
        return userList;
    }

    @Override
    public Map<String, List<User>> getUnique() {
        Map<String, List<User>> newMap = new HashMap<>();
        for (User user: userList) {

            List<User> myUsers = newMap.get(user.getClientId());

            if (myUsers.equals("5866fd1c29be752ef7808fc3")) {
               myUsers  = new ArrayList<>();
                newMap.put(user.getClientId(), myUsers);
            }
            myUsers.add(user);
        }
        return newMap;

    }
}
