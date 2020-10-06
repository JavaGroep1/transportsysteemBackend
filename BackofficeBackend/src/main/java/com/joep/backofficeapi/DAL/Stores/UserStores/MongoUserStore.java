package com.joep.backofficeapi.DAL.Stores.UserStores;

import com.google.gson.Gson;
import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.mongodb.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MongoUserStore implements IUserStore {

    MongoClientURI uri;
    MongoClient mongoClient;
    DB database;
    DBCollection collection;

    public MongoUserStore() throws UnknownHostException {

        uri = new MongoClientURI(
                "mongodb://127.0.0.1:27017/?compressors=zlib&gssapiServiceName=mongodb");
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDB("test");
        collection = database.getCollection("Accounts");
    }
    @Override
    public ArrayList<ApplicationUser> getAllUsers() {
        return null;
    }

    @Override
    public ApplicationUser getUserByName(String name) throws IOException {

        DBObject query = new BasicDBObject("username", name);
        DBCursor cursos = collection.find(query);
        DBObject obj = cursos.one();
        String json = obj.toString();
        Gson gson = new Gson();
        ApplicationUser accountFound = gson.fromJson(json, ApplicationUser.class);
        return accountFound;
    }

    @Override
    public Boolean createUser(ApplicationUser user) {

        collection.insert(userToDBObject(user));

        return true;
    }
    @Override
    public Boolean emailExists(String email) {
        DBObject query = new BasicDBObject("email", email);
        if ( collection.find(query).hasNext()) return true;

        return false;
    }
    @Override
    public Boolean usernameExists(String name) {
        DBObject query = new BasicDBObject("username", name);
        if ( collection.find(query).hasNext()) return true;

        return false;
    }
    private DBObject userToDBObject(ApplicationUser user){
        BasicDBObject obj = new BasicDBObject()
                .append("id", user.getId())
                .append("email", user.getEmail())
                .append("roles", user.getRoles())
                .append("username", user.getUsername())
                .append("password", user.getPassword())
                .append("accountNonExpired", user.isAccountNonExpired())
                .append("accountNonLocked", user.isAccountNonLocked())
                .append("credentialsNonExpired", user.isCredentialsNonExpired())
                .append("enabled", user.isEnabled());

        return obj;
    }
}
