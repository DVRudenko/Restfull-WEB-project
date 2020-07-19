package by.rudenko.imarket.impl;

import by.rudenko.imarket.AdvertDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Advert;
import by.rudenko.imarket.model.AdvertTopic;
import by.rudenko.imarket.model.AdvertTopic_;
import by.rudenko.imarket.model.Advert_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class AdvertDaoImpl extends AbstractDao<Advert, Long> implements AdvertDao {

    private static final Logger LOGGER = LogManager.getLogger(AdvertDaoImpl.class);

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
        return getWithPagination(pageNumber, pageSize, select).getResultList();
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

        Advert advert = em.createQuery(select).getResultList().get(0);

        if (advert != null) {
            return advert;
        } else {
            LOGGER.error("No such Advert ID-" + id);
            throw new NoSuchIdException("No such Advert ID-" + id);
        }
    }

    // сортируем объявления по рангу (VIP,Prior,Usual)
    // и TODO рейтинга продавца
    @Override
    public List<Advert> getSortedAdverts(int pageNumber, int pageSize) {
        LOGGER.info("Get sorted adverts ");
        //получаем все поля с ленивой инициализацией
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = getAdvertRoot(cq);
        //сортируем с учетом статуса объявлений (1-ми идут VIP)
        CriteriaQuery<Advert> select =
                cq.select(root)
                        .orderBy(
                                cb.desc(root.get(Advert_.advertRank))
                        );
        return getWithPagination(pageNumber, pageSize, select).getResultList();
    }


    // сортируем объявления по рангу (VIP,Prior,Usual)
    // с учетом темы объявления
    @Override
    public List<Advert> getSortedAdvertsByTopic(String topic, int pageNumber, int pageSize) {
        LOGGER.info("Get sorted adverts by topic");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = getAdvertRoot(cq);
        //делаем Join с таблицей AdverTopic по advertTopic
        Join<Advert, AdvertTopic> advTopicRoot = root.join(Advert_.advertTopic, JoinType.LEFT);

        CriteriaQuery<Advert> select =
                cq.select(root).where(
                        cb.equal(
                                advTopicRoot.get(AdvertTopic_.topicName), topic)) //выбираем тему
                        .orderBy(
                                cb.desc(root.get(Advert_.advertRank)) //сортируем по рангу объявления
                        );
        return getWithPagination(pageNumber, pageSize, select).getResultList();
    }

    public List<Advert> getSortedAdvertsByUserRank(int pageNumber, int pageSize) {
        LOGGER.info("Get sorted adverts by User Rank");
        //получаем все поля с ленивой инициализацией
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> root = cq.from(Advert.class);

        CriteriaQuery<Advert> select =
                cq.select(root)
                        .orderBy(
                                cb.desc(root.get("user").get("profile").get("userRank")),// сортировка по рангу пользователя
                                cb.desc(root.get(Advert_.advertRank) //+сортируем по рангу объявления
                                )
                        );

        return getWithPagination(pageNumber, pageSize, select).getResultList();

    }


    //внутренний метод используем пагинацию
    private TypedQuery<Advert> getWithPagination(int pageNumber, int pageSize, CriteriaQuery<Advert> select) {
        TypedQuery<Advert> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery;
    }

    //внутренний метод сделать Join полей
    private Root<Advert> getAdvertRoot(CriteriaQuery<Advert> cq) {
        Root<Advert> root = cq.from(Advert.class);
        root.fetch(Advert_.user, JoinType.LEFT);
        root.fetch(Advert_.advertTopic, JoinType.LEFT);
        root.fetch(Advert_.advertRank, JoinType.LEFT);
        return root;
    }


}

