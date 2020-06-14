package by.rudenko.imarket;

import by.rudenko.imarket.dto.CommentDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface CommentService {
    //вывести полный список объявлений с вложениями
    List<CommentDTO> getFullCommentsList(int pageNumber, int pageSize);

    CommentDTO findById(Long id) throws NoSuchIdException;

    List<CommentDTO> getAllCommentsList(int pageNumber, int pageSize);

    boolean addNewComment(CommentDTO commentDTO);

    boolean deleteComment(CommentDTO commentDTO);

    boolean update(CommentDTO commentDTO);
}



