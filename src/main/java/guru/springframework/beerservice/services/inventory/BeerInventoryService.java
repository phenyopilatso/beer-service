package guru.springframework.beerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getQuantityOnHand(UUID beerId);
}
