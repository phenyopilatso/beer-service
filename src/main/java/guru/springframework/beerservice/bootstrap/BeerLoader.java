package guru.springframework.beerservice.bootstrap;

import guru.springframework.beerservice.domain.Beer;
import guru.springframework.beerservice.repositories.BeerRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepositories beerRepositories;

    public BeerLoader(BeerRepositories beerRepositories) {
        this.beerRepositories = beerRepositories;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepositories.count() == 0){

            beerRepositories.save(Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .quantityToBrew(200)
                .minOnHand(45)
                .upc(648937489343L)
                .price(new BigDecimal("20.00"))
                .build());

            beerRepositories.save(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .quantityToBrew(100)
                .minOnHand(85)
                .upc(78945564664L)
                .price(new BigDecimal("10.00"))
                .build());
        }
    }
}
