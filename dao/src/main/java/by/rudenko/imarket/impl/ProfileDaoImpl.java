package by.rudenko.imarket.impl;


import by.rudenko.imarket.UserDao;
import by.rudenko.imarket.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao <User, Long> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger("imarketUser");

    public UserDaoImpl() {
        super(User.class);
    }

    //void setGuestStatus(long id, Utils.GuestStatus status) throws NoSuchIdException;


}

