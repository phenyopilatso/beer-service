package guru.springframework.beerservice.web.mappers;

import guru.sfg.brewery.model.BeerDto;
import guru.springframework.beerservice.domain.Beer;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
