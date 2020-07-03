package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertTopicDao;
import by.rudenko.imarket.model.AdvertTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertTopicDaoImpl extends AbstractDao<AdvertTopic, Long> implements AdvertTopicDao {

    private static final Logger LOGGER = LogManager.getLogger("imarketAdvertTopic");

    public AdvertTopicDaoImpl() {
        super(AdvertTopic.class);
    }


}

