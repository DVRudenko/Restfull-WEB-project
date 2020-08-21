package by.rudenko.imarket;

import by.rudenko.imarket.model.User;


public interface UserDao extends GenericDao<User, Long> {

    /**
     * Find User Entity for it's name (Login)
     *
     * @param username - name of user
     * @return User Entity
     * Note: can return null if username not found
     * Don't trows exception
     */
    User findByUsername(String username);
}

