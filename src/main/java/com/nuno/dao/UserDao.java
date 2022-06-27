package com.nuno.dao;

import java.util.List;

import com.nuno.beans.UserEntity;

public interface UserDao {
    void ajouter( UserEntity utilisateur ) throws DaoException;
    List<UserEntity> lister() throws DaoException;
}