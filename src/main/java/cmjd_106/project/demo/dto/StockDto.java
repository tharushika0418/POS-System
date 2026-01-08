package cmjd_106.project.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
    private Long productId;
    private Integer quantity;
    private String transactionType; // "IN" or "OUT"
    private String remarks;
}