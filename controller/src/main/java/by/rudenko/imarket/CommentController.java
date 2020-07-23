package by.rudenko.imarket;

import by.rudenko.imarket.dto.CommentDTO;
import by.rudenko.imarket.dto.CommentShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for comments requests (get, post, delete, etc.)
 *
 * @author Dmitry Rudenko
 * @version 1.0
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    //количество записей на страницу
    @Value("${defaultPageSize}")
    public Integer defaultPageSize;

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<CommentShortDTO> getAllShortComments(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        //проверка на корректность параметров пагинации
        CheckPagination checkPagination =
                new CheckPagination(pageNumber, pageSize,
                        commentService.entityCount(), defaultPageSize)
                        .check();
        pageNumber = checkPagination.getPageNumber();
        pageSize = checkPagination.getPageSize();

        return commentService.getAllShortComments(pageNumber, pageSize);
    }

    @GetMapping(value = "/full")
    public List<CommentDTO> getAllComments(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return commentService.getFullCommentsList(pageNumber, pageSize);
    }

    @GetMapping(value = "/count")
    public Long getUsersCount() {
        return commentService.entityCount();
    }

    @GetMapping(value = "/{id}")
    public CommentDTO getAllComments(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return commentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewComment(@RequestBody CommentDTO commentDTO) {
        commentService.addNewComment(commentDTO);
        return ResponseEntity.ok("comment saved");
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        commentService.deleteComment(commentService.findById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@RequestBody CommentDTO commentDTO) {
        commentService.update(commentDTO);
    }

}
