package by.rudenko.imarket.impl;

import by.rudenko.imarket.UserDao;
import by.rudenko.imarket.model.User;
import by.rudenko.imarket.model.User_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        CriteriaQuery<User> select = cq.select(root)
                .where(cb.equal(
                        root.get(User_.login), username));

        LOGGER.info("Find by username " + username);
        List<User> response = em.createQuery(select).getResultList();
        //проверяем на пустой ответ
        User user;
        if (response.size() == 0) {
            user = null;
        } else {
            user = response.get(0);
        }

        return user;
    }
}

