package uz.pdp.shopping_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProduct {
    private UUID orderId;
    private UUID productId;
    private Integer amount;
}
