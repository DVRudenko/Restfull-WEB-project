package by.rudenko.imarket.impl;

import by.rudenko.imarket.CommentDao;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CommentDaoImpl extends AbstractDao<Comment, Long> implements CommentDao {

    private static final Logger LOGGER = LogManager.getLogger("imarket");

    public CommentDaoImpl() {
        super(Comment.class);
    }

    // список всех Comment без сортировки
    @Override
    public List<Comment> getFullComments(int pageNumber, int pageSize) {
        LOGGER.info("Get full comments list ");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
        Root<Comment> root = getCommentRoot(cq);

        CriteriaQuery<Comment> select = cq.select(root);
        //используем пагинацию
        TypedQuery<Comment> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    //внутренний метод сделать Join вложенных полей
    private Root<Comment> getCommentRoot(CriteriaQuery<Comment> cq) {
        Root<Comment> root = cq.from(Comment.class);
        root.fetch(Comment_.user, JoinType.INNER);
        root.fetch(Comment_.advert, JoinType.INNER);
        //делаем вложенный fetch 2-го уровня
        Fetch<Comment, Advert> advRoot = root.fetch(Comment_.advert);
        Fetch<Advert, User> userRoot = advRoot.fetch(Advert_.user);
        Fetch<Advert, AdvertTopic> topicRoot = advRoot.fetch(Advert_.advertTopic);
        Fetch<Advert, AdvertRank> rankRoot = advRoot.fetch(Advert_.advertRank);

        return root;
    }

    //получаем все поля Comment по ID с ленивой инициализацией
    @Override
    public Comment getFullCommentByID(final Long id) throws NoSuchIdException {
        LOGGER.info("Get full comment by Id ");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
        Root<Comment> root = getCommentRoot(cq);

        CriteriaQuery<Comment> select =
                cq.select(root)
                        .where(cb.equal(
                                root.get(Comment_.id), id)
                        );

        List<Comment> comment = em.createQuery(select).getResultList();

        if (comment.size() > 0) {
            return comment.get(0);
        } else {
            LOGGER.error("No such Comment ID" + id);
            throw new NoSuchIdException("No such Comment ID" + id);
        }
    }


}

