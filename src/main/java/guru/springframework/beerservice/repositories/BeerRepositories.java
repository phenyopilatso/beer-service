package guru.springframework.beerservice.repositories;

import guru.springframework.beerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepositories extends PagingAndSortingRepository<Beer, UUID> {


}
