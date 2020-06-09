package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.User;
import by.rudenko.imarket.utils.Utils;

import java.util.List;


public interface UserDao extends GenericDao <User, Long> {

    void setGuestStatus(long id, Utils.GuestStatus status) throws NoSuchIdException;


}

