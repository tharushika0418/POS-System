package cmjd_106.project.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmjd_106.project.demo.entity.Order;
import cmjd_106.project.demo.entity.Product;
import cmjd_106.project.demo.repository.OrderRepository;
import cmjd_106.project.demo.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
          
    }
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setTotalPrice(order.getTotalPrice());
            existingOrder.setOrderDate(order.getOrderDate());
            return orderRepository.save(existingOrder);
        }
        return null;
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order addProductsToOrder(Long orderId, Long productId, int quantity) {
         Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            Product product=productRepository.findById(productId).orElse(null);
            if(product!=null){

                //getter of ordered_products
                order.getOrdered_products().add(product);
                order.setTotalPrice(order.getTotalPrice()+product.getPrice()*quantity);
                return orderRepository.save(order);
            }
        }
        return null;
      }
    public Order deleteProductFromOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                order.getOrdered_products().remove(product);
                order.setTotalPrice(order.getTotalPrice() - product.getPrice());
                return orderRepository.save(order);
            }
        }
        return null;
    }
}
