package cmjd_106.project.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cmjd_106.project.demo.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    Order addProductsToOrder(Long orderId, Long productId,int quantity);
    Order deleteProductFromOrder(Long orderId, Long productId);

    
}
