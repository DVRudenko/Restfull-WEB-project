package by.rudenko.imarket.impl;

import by.rudenko.imarket.ProfileDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Profile;
import by.rudenko.imarket.model.Profile_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProfileDaoImpl extends AbstractDao<Profile, Long> implements ProfileDao {

    private static final Logger LOGGER = LogManager.getLogger("imarket");

    public ProfileDaoImpl() {
        super(Profile.class);
    }

    //получаем все поля Profile с ленивой инициализацией
    //используем пагинацию
    @Override
    public List<Profile> getFullProfiles(int pageNumber, int pageSize) {
        LOGGER.info("Get full profiles list");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> root = cq.from(Profile.class);
        root.fetch(Profile_.user, JoinType.INNER);

        CriteriaQuery<Profile> select = cq.select(root);

        TypedQuery<Profile> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }


    @Override
    public Profile getFullProfileByID(final Long id) throws NoSuchIdException {
        LOGGER.info("Get full profile by Id ");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> root = cq.from(Profile.class);
        root.fetch(Profile_.user, JoinType.INNER);

        CriteriaQuery<Profile> select =
                cq.select(root)
                        .where(cb.equal(
                                root.get(Profile_.id), id)
                        );

        List<Profile> profile = em.createQuery(select).getResultList();

        if (profile.size() >0) {
            return profile.get(0);
        } else {
            LOGGER.error("No such Profile ID" + id);
            throw new NoSuchIdException("No such Profile ID" + id);
        }
    }

}

