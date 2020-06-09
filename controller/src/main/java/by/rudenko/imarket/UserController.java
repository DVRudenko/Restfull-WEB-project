package by.rudenko.imarket;

import by.rudenko.imarket.dto.OrderDTO;
import by.rudenko.imarket.dto.OrderShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController <T extends OrderShortDTO> {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    //тип Get /orders/ - получить список всех заказов
    @GetMapping
    public List<OrderDTO> getOrders(int pageNumber, int pageSize) {
        return orderService.getAllOrdersList( pageNumber, pageSize);
    }

    //тип Get /orders/id
    @GetMapping(value = "/{id}")
    public <T extends OrderShortDTO> T  getOrders(@PathVariable(value = "id") Long id,
                       @RequestParam(required = false, name = "fetch") String param
    ) throws NoSuchIdException {
        if (param.equals("short")) {
            return (T) orderService.findShortById(id);
        } else {
            return (T) orderService.findById(id);
        }

    }

    //тип Get /orders/id
    @GetMapping(value = "cost/{id}")
    public int getOrdersCost (@PathVariable(value = "id") Long id) throws NoSuchIdException {
        return orderService.getOrderCost(orderService.findById(id));
    }

    //тип Post /orders/JSON
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addNewOrder (@RequestBody OrderDTO orderDTO) {
        orderService.addNewOrder(orderDTO);
    }

    //тип Delete /rooms/id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable(value = "id") Long id) throws NoSuchIdException {

        orderService.deleteOrder(orderService.findById(id));
    }
}
