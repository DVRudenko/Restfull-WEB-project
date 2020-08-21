package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertRankDao;
import by.rudenko.imarket.model.AdvertRank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertRankDaoImpl extends AbstractDao<AdvertRank, Long> implements AdvertRankDao {

    private static final Logger LOGGER = LogManager.getLogger("imarket");

    public AdvertRankDaoImpl() {
        super(AdvertRank.class);
    }


}

