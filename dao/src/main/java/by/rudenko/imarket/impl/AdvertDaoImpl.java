package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class AdvertDaoImpl extends AbstractDao<Advert, Long> implements AdvertDao {

    private static final Logger LOGGER = LogManager.getLogger("imarketAdvert");

    public AdvertDaoImpl() {
        super(Advert.class);
    }

    // список всех объявлений без сортировки
    @Override
    public List<Advert> getFullAdverts(int pageNumber, int pageSize) {
        LOGGER.info("Get full adverts list ");
        //получаем все поля с ленивой инициализацией
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = getAdvertRoot(cq);
        CriteriaQuery<Advert> select = cq.select(root);
        //используем пагинацию
        TypedQuery<Advert> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    //внутренний метод сделать Join полей
    private Root<Advert> getAdvertRoot(CriteriaQuery<Advert> cq) {
        Root<Advert> root = cq.from(Advert.class);
        root.fetch(Advert_.user, JoinType.INNER);
        root.fetch(Advert_.advertTopic, JoinType.INNER);
        root.fetch(Advert_.advertRank, JoinType.INNER);
        return root;
    }

    //получаем все поля Advert по ID с ленивой инициализацией
    @Override
    public Advert getFullAdvertByID(final Long id) throws NoSuchIdException {
        LOGGER.info("Get full advert by Id ");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = getAdvertRoot(cq);

        CriteriaQuery<Advert> select =
                cq.select(root)
                        .where(cb.equal(
                                root.get(Advert_.id), id)
                        );

        Advert advert = em.createQuery(select).getSingleResult();

        if (advert != null) {
            return advert;
        } else {
            LOGGER.error("No such Advert ID" + id);
            throw new NoSuchIdException("No such Advert ID" + id);
        }
    }

    // сортируем объявления по рангу (VIP,Prior,Usual)
    // и рейтинга продавца
    @Override
    public List<Advert> getSortedAdverts(int pageNumber, int pageSize) {
        LOGGER.info("Get sorted adverts ");
        //получаем все поля с ленивой инициализацией
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = getAdvertRoot(cq);
/*
        //запрос
        CriteriaQuery<Profile> cq2 = cb.createQuery(Profile.class);
        Root<Profile> profile = cq2.from(Profile);

        Join<Profile, Advert> prof = profile.join(Profile.class);
        profile.fetch(Profile_.user, JoinType.LEFT);*/

        //конец join

        //сортируем с учетом статуса объявлений (1-ми идут VIP)
        CriteriaQuery<Advert> select =
                cq.select(root)
                        .orderBy(
                                cb.desc(root.get(Advert_.advertRank)),
                                // TODO как тут сделать Join из таблицы Profiles_userRank по user???
                                cb.desc(root.get(Advert_.advertTopic))

                        );
        //используем пагинацию
        TypedQuery<Advert> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    // сортируем объявления по рангу (VIP,Prior,Usual)
    // с учетом темы
    //с пагинацией
    @Override
    public List<Advert> getSortedAdvertsByTopic(AdvertTopic advertTopic, int pageNumber, int pageSize) {
        LOGGER.info("Get sorted adverts ");
        //получаем все поля с ленивой инициализацией
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = getAdvertRoot(cq);

        /*Join<Advert, Profile> profile = cq.from(Profile.class);
        profile.fetch(Profile_.user, JoinType.LEFT);*/

        /*Subquery sub = cq.subquery(Long.class);
        Root<AdvertTopic> subRoot = sub.from(AdvertTopic.class);
        //
        SetJoin<AdvertTopic,Advert> subTopic = subRoot.join(AdvertTopic_.id);*/


        //сортируем с учетом статуса объявлений (1-ми идут VIP)
        CriteriaQuery<Advert> select =
                cq.select(root)
                        //.where(cb.equal(root.get(AdvertTopic_.topicName), advertTopic))
                        .orderBy(
                                cb.desc(root.get(Advert_.advertRank)),
                                // TODO как тут сделать Join из таблицы Profiles_userRank по user???
                                cb.desc(root.get(Advert_.advertTopic))

                        );
        //используем пагинацию
        TypedQuery<Advert> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }


}

