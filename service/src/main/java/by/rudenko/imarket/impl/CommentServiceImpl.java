package by.rudenko.imarket.impl;

import by.rudenko.imarket.CommentDao;
import by.rudenko.imarket.CommentService;
import by.rudenko.imarket.dto.CommentDTO;
import by.rudenko.imarket.dto.CommentDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import by.rudenko.imarket.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private  final CommentDao commentDao;
    private  final ModelMapper modelMapper;

    public CommentServiceImpl(CommentDao commentDao, ModelMapper modelMapper) {
        this.commentDao = commentDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean addNewComment(CommentDTO commentDTO) {
        // маппинг из ДТО в  Entity
        final Comment comment = modelMapper.map(commentDTO, Comment.class);
        commentDao.save(comment);
        return true;
    }

    //вывести полный список объявлений с вложениями
    @Override
    public List<CommentDTO> getFullCommentsList(int pageNumber, int pageSize) {

        return commentDao.getFullComments(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, CommentDTO.class))
                .collect(Collectors.toList());
    }


    //вывести полное объявлений с вложениями по Id
    @Override
    public CommentDTO findById(Long id) throws NoSuchIdException {
        final Comment commentEntity = commentDao.getFullCommentByID(id);

        return modelMapper.map (commentEntity, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getAllCommentsList(int pageNumber, int pageSize) {

        return commentDao.getAll(pageNumber, pageSize).stream()
                .map(x -> modelMapper.map(x, CommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteComment(CommentDTO commentDTO) {

        final Comment comment = modelMapper.map(commentDTO, Comment.class);
        commentDao.delete(comment);
        return true;
    }

    @Override
    public boolean update(CommentDTO commentDTO) {
        final Comment comment = modelMapper.map(commentDTO, Comment.class);
        commentDao.update(comment);
        return true;
    }
}
