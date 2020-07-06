package by.rudenko.imarket.impl;

import by.rudenko.imarket.DebateDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class DebateDaoImpl extends AbstractDao<Debate, Long> implements DebateDao {

    private static final Logger LOGGER = LogManager.getLogger("imarket");

    public DebateDaoImpl() {
        super(Debate.class);
    }

    // список всех Debate без сортировки
    @Override
    public List<Debate> getFullDebates(int pageNumber, int pageSize) {
        LOGGER.info("Get full debates list ");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Debate> cq = cb.createQuery(Debate.class);
        Root<Debate> root = getDebateRoot(cq);

        CriteriaQuery<Debate> select = cq.select(root);
        //используем пагинацию
        TypedQuery<Debate> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    //внутренний метод сделать Join вложенных полей
    private Root<Debate> getDebateRoot(CriteriaQuery<Debate> cq) {
        Root<Debate> root = cq.from(Debate.class);
        root.fetch(Debate_.user, JoinType.INNER);
        root.fetch(Debate_.sellHistory, JoinType.INNER);
        //делаем вложенный fetch 2-го уровня
        Fetch<Debate, SellHistory> sellHistoryRoot = root.fetch(Debate_.sellHistory);
        Fetch<SellHistory, User> buyerRoot = sellHistoryRoot.fetch(SellHistory_.buyer);
        Fetch<SellHistory, User> sellerRoot = sellHistoryRoot.fetch(SellHistory_.seller);
        Fetch<SellHistory, Advert> advRoot = sellHistoryRoot.fetch(SellHistory_.advert);
        //делаем вложенный fetch 3-го уровня
        Fetch<Advert, User> userRoot = advRoot.fetch(Advert_.user);
        Fetch<Advert, AdvertTopic> topicRoot = advRoot.fetch(Advert_.advertTopic);
        Fetch<Advert, AdvertRank> rankRoot = advRoot.fetch(Advert_.advertRank);

        return root;
    }

    //получаем все поля Debate по ID с ленивой инициализацией
    @Override
    public Debate getFullDebateByID(final Long id) throws NoSuchIdException {
        LOGGER.info("Get full debate by Id ");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Debate> cq = cb.createQuery(Debate.class);
        Root<Debate> root = getDebateRoot(cq);

        CriteriaQuery<Debate> select =
                cq.select(root)
                        .where(cb.equal(
                                root.get(Debate_.id), id)
                        );

        Debate debate = em.createQuery(select).getSingleResult();

        if (debate != null) {
            return debate;
        } else {
            LOGGER.error("No such Debate ID" + id);
            throw new NoSuchIdException("No such Debate ID" + id);
        }
    }


}

