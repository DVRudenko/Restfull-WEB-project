package by.rudenko.imarket;

import by.rudenko.imarket.dto.CommentDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController<T extends CommentDTO> {

    private final CommentService commentService;

    //TODO как добавить зависимость из модуля Launcher без циклической ссылки???
    //private final IMarketConfig iMarketConfig;
    //IMarketConfig.defaultPageSize //количество записей на страницу по умолчанию (из файла свойств)

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }


    //тип Get /comments/ - получить весь список с пагинацией
    @GetMapping
    public List<CommentDTO> getAllComments(
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return commentService.getFullCommentsList(pageNumber, pageSize);
    }

    //тип Get /guests/count - получить количество строк в таблице (пользователей)
    @GetMapping(value = "/count")
    public Long getUsersCount()  {
        return commentService.entityCount();
    }

    //тип Get /guests/id - получить сущность по Id
    @GetMapping(value = "/{id}")
    public CommentDTO getAllComments(@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return commentService.findById(id);
    }

    //тип Post /comments/JSON добавить новую запись
    // TODO посмотреть как отрабатывает возврат ответа
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewComment (@RequestBody CommentDTO commentDTO) {
        commentService.addNewComment(commentDTO);
        return ResponseEntity.ok("comment saved");
    }



    //тип Delete /rooms/id удалить запись по Id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        commentService.deleteComment(commentService.findById(id));
    }

    //тип Put /comments/JSON обновить запись
    //TODO как лучше через POST или PUT
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateComment (@RequestBody CommentDTO commentDTO) {
        commentService.update(commentDTO);
    }

}
