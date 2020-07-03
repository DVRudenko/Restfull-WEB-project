package by.rudenko.imarket;

import by.rudenko.imarket.model.User;


public interface UserDao extends GenericDao<User, Long> {


    User findByUsername(String username);
}

