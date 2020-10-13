package guru.springframework.beerservice.services;

import guru.springframework.beerservice.web.model.BeerDto;
import guru.springframework.beerservice.web.model.BeerPagedList;
import guru.springframework.beerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String beerUpc);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
