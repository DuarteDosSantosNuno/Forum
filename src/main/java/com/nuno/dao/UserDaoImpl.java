package com.nuno.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.nuno.beans.BeanException;
import com.nuno.beans.UserEntity;

public class UserDaoImpl implements UserDao {
    private DaoFactory daoFactory;

    UserDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(UserEntity utilisateur) throws DaoException {
//    	MongoClient connexion = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connexion = daoFactory.getConnection();
//            preparedStatement = connexion.prepareStatement("INSERT INTO User(username, password) VALUES(?, ?);");
//            preparedStatement.setString(1, utilisateur.getUsername());
//            preparedStatement.setString(2, utilisateur.getPassword());
//
//            preparedStatement.executeUpdate();
//            connexion.commit();
//        } catch (SQLException e) {
//            try {
//                if (connexion != null) {
//                    connexion.rollback();
//                }
//            } catch (SQLException e2) {
//            }
//            throw new DaoException("Impossible de communiquer avec la base de données");
//        }
//        finally {
//            try {
//                if (connexion != null) {
//                    connexion.close();  
//                }
//            } catch (SQLException e) {
//                throw new DaoException("Impossible de communiquer avec la base de données");
//            }
//        }

    }

    @Override
    public List<UserEntity> lister() throws DaoException {
    	List<UserEntity> users = new ArrayList<UserEntity>();
    	MongoClient connexion = null;

        try {
            connexion = daoFactory.getConnection();
            MongoDatabase database = connexion.getDatabase("Forum");
            MongoCollection<Document> col = database.getCollection("User");
            MongoCursor<Document> cursor = col.find().iterator();
            
            try {           	
                while(cursor.hasNext()) {   
                	Document doc = cursor.next();
                    String username = doc.get("username").toString();
                    String password = doc.get("password").toString();
                    UserEntity user = new UserEntity();
                    user.setUsername(username);
                    user.setPassword(password);
                    users.add(user);
                }
            } finally {
                cursor.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (BeanException e) {
            throw new DaoException("Les données de la base sont invalides");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (Exception e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return users;
    }

}