package by.rudenko.imarket;

import by.rudenko.imarket.model.User;


public interface UserDao extends GenericDao<User, Long> {

    /**
     * Find User Entity for it's name
     *
     * @param username - name of user
     * @return User Entity
     */
    User findByUsername(String username);
}

