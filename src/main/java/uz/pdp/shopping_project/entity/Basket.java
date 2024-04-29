package uz.pdp.shopping_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.shopping_project.repo.ProductRepo;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    private Map<Product, Integer> basketProducts = ProductRepo.countProducts();

    public void incrementProductQuantity(UUID productId) {
        for (Map.Entry<Product, Integer> entry : basketProducts.entrySet()) {
            if (entry.getKey().getId().equals(productId)) {
                int currentQuantity = entry.getValue();
                entry.setValue(currentQuantity + 1);
                return;
            }
        }
    }

    public void decrementProductQuantity(UUID productId) {
        for (Map.Entry<Product, Integer> entry : basketProducts.entrySet()) {
            if (entry.getKey().getId().equals(productId)) {
                int currentQuantity = entry.getValue();
                if (currentQuantity > 0) {
                    entry.setValue(currentQuantity - 1);
                }
                return;
            }
        }
    }
}
