package guru.sfg.beerservice.model;

import guru.sfg.beerservice.web.model.BeerDto;
import guru.sfg.beerservice.web.model.BeerStyleEnum;
import guru.sfg.beerservice.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {
    public BeerDto getDto(){
        return  BeerDto.builder()
                .beerName("BeerName")
                .beerStyle(BeerStyleEnum.GOSE)
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(BeerLoader.BEER_2_UPC)
                .quantityOnHand(45)
                .build();
    }
}
