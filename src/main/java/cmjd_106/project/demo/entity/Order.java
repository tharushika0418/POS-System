package cmjd_106.project.demo.entity;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private LocalDateTime orderDate;
    
    // NEW: Add invoice number field
    private String invoiceNumber;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "product_id")
        )
    
    private java.util.List<Product> ordered_products;
    
    // NEW: Add method to generate invoice number
    public void generateInvoiceNumber() {
        this.invoiceNumber = "INV-" + System.currentTimeMillis();
    }
}