package uz.pdp.shopping_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.shopping_project.repo.CategoryRepo;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private UUID id;
    private String name;
    private Integer price;
    private UUID categoryId;
    private byte[] photo;

    public String getCategoryName() {
        Category byId = CategoryRepo.findById(categoryId);
        return byId.getName();
    }


}
