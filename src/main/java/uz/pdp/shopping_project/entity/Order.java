package uz.pdp.shopping_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.shopping_project.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private UUID id;
    private LocalDateTime dateTime;
    private UUID userId;
    private Status status;
}
