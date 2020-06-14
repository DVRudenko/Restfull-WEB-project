package by.rudenko.imarket;

import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Comment;

import java.util.List;


public interface CommentDao extends GenericDao <Comment, Long> {


    // список всех Comment без сортировки
    List<Comment> getFullComments(int pageNumber, int pageSize);

    //получаем все поля Comment по ID с ленивой инициализацией
    Comment getFullCommentByID(Long id) throws NoSuchIdException;
}

