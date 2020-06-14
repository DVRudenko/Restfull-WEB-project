package by.rudenko.imarket.impl;

import by.rudenko.imarket.SellHistoryDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.*;
import by.rudenko.imarket.model.SellHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class SellHistoryDaoImpl extends AbstractDao <SellHistory, Long> implements SellHistoryDao {

    private static final Logger LOGGER = LogManager.getLogger("imarketSellHistory");

    public SellHistoryDaoImpl() {
        super(SellHistory.class);
    }

    // список всех SellHistory без сортировки
    @Override
    public List<SellHistory> getFullSellHistories(int pageNumber, int pageSize) {
        LOGGER.info("Get full sellHistories list");
        //получаем все вложенные поля с ленивой инициализацией
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SellHistory> cq = cb.createQuery(SellHistory.class);
        Root<SellHistory> root = getSellHistoryRoot(cq);
        CriteriaQuery<SellHistory> select = cq.select(root);
        //используем пагинацию
        TypedQuery<SellHistory> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    //внутренний метод сделать Join полей
    private Root<SellHistory> getSellHistoryRoot(CriteriaQuery<SellHistory> cq) {
        Root<SellHistory> root = cq.from(SellHistory.class);
        root.fetch(SellHistory_.buyer, JoinType.LEFT);
        root.fetch(SellHistory_.seller, JoinType.LEFT);
        root.fetch(SellHistory_.advert, JoinType.LEFT);
        //делаем вложенный fetch 2-го уровня
        Fetch<SellHistory, Advert> advRoot = root.fetch(SellHistory_.advert);
        Fetch<Advert, User> userRoot = advRoot.fetch(Advert_.user);
        Fetch<Advert, AdvertTopic> topicRoot = advRoot.fetch(Advert_.advertTopic);
        Fetch<Advert, AdvertRank> rankRoot = advRoot.fetch(Advert_.advertRank);

        return root;
    }

    //получаем все поля SellHistory по ID с ленивой инициализацией
    @Override
    public SellHistory getFullSellHistoryByID(final Long id) throws NoSuchIdException {
        LOGGER.info("Get full sellHistory by Id ");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SellHistory> cq = cb.createQuery(SellHistory.class);
        Root<SellHistory> root = getSellHistoryRoot(cq);

        CriteriaQuery<SellHistory> select =
                cq.select(root)
                        .where(cb.equal(
                                root.get(SellHistory_.id), id)
                        );

        SellHistory sellHistory = em.createQuery(select).getSingleResult();

        if (sellHistory != null) {
            return sellHistory;
        } else {
            LOGGER.error("No such SellHistory ID" + id);
            throw new NoSuchIdException("No such SellHistory ID" + id);
        }
    }



}

