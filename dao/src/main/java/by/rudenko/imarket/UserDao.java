package by.rudenko.imarket;

import by.rudenko.imarket.model.*;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.utils.Utils;

import java.util.List;


public interface GuestDao extends GenericDao <User, Long> {

    void setGuestStatus(long id, Utils.GuestStatus status) throws NoSuchIdException;

    List<User> getAllGuestsByStatus(Utils.GuestStatus status);

    int getGuestsNumbers(Utils.GuestStatus status);

    List<User> getAllGuestsByNames();

    List<User> getLiveGuestsByNames();


}

