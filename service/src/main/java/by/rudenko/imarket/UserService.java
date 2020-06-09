package by.rudenko.imarket;

import by.rudenko.imarket.dto.OrderDTO;
import by.rudenko.imarket.dto.OrderShortDTO;
import by.rudenko.imarket.exception.NoSuchIdException;

import java.util.List;


public interface OrderService {
    OrderDTO findById(long id) throws NoSuchIdException;

    OrderShortDTO findShortById(long id) throws NoSuchIdException;

    List<OrderDTO> getAllOrdersList(int pageNumber, int pageSize);

    int getOrderCost(OrderDTO orderDTO);

    boolean addNewOrder(OrderDTO orderDTO);

    boolean deleteOrder(OrderDTO byId);
}



