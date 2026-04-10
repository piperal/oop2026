package ee.piperal.veebipood.dto;

import lombok.Data;

@Data
public class OrderRowDto { //DTO --> Data Transfer Object
    Long productId;
    int quantity;
}

