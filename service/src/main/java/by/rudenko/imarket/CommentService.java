package by.rudenko.imarket;

import by.rudenko.imarket.dto.CommentDTO;
import by.rudenko.imarket.dto.CommentShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface CommentService {

    List<CommentDTO> getFullCommentsList(int pageNumber, int pageSize);

    CommentDTO findById(Long id) throws NoSuchIdException;

    List<CommentDTO> getAllCommentsList(int pageNumber, int pageSize);

    boolean addNewComment(CommentDTO commentDTO);

    boolean deleteComment(CommentDTO commentDTO);

    boolean update(CommentDTO commentDTO);

    Long entityCount();

    List<CommentShortDTO> getAllShortComments(int pageNumber, Integer pageSize);
}



