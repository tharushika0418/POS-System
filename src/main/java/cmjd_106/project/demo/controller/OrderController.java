package cmjd_106.project.demo.controller;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cmjd_106.project.demo.dto.OrderproductDto;
import cmjd_106.project.demo.entity.Order;
import cmjd_106.project.demo.service.OrderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController

@CrossOrigin(origins="*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    

    
@GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/order")
    public Order createOrder() {
        Order order = new Order();
        order.setTotalPrice(0.0);
        order.setOrderDate(LocalDateTime.now());
        order.generateInvoiceNumber(); // NEW: Generate invoice number
        return orderService.createOrder(order);
       
    }

    @PostMapping("/order/{id}/addProduct")
    public Order addProductTOrder(@PathVariable Long id,@RequestBody OrderproductDto orderproductDto) {

        return orderService.addProductsToOrder(id,orderproductDto.getProduct_id(),orderproductDto.getQuantity());
        
    }
    

    @PutMapping("order/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @DeleteMapping("order/{orderId}/product/{productId}")
    public Order deleteProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.deleteProductFromOrder(orderId, productId);
    }
    
    // NEW: Add invoice retrieval endpoint
    @GetMapping("/order/invoice/{id}")
    public ResponseEntity<Order> getOrderInvoice(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    
}