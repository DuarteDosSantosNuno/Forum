package com.nuno.dao;

import java.sql.SQLException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mongodb.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory("mongodb://127.0.0.1:27017", "admin", "tssnadmin");
        
        return instance;
    }

    public MongoClient getConnection() throws SQLException {
    	MongoClient connexion = MongoClients.create("mongodb://localhost:27019");
        return connexion; 
    }

    // Récupération du Dao
    public UserDao getUserDao() {
        return new UserDaoImpl(this);
    }
}