package guru.sfg.beerservice.web.mappers;

import guru.sfg.beerservice.web.model.BeerDto;
import guru.sfg.beerservice.domain.Beer;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
