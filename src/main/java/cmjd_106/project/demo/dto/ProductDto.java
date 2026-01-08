package cmjd_106.project.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private Integer quantity;
    private Double price;
    private Long category_id;   
}
