package guru.springframework.beerservice.bootstrap;

import guru.springframework.beerservice.domain.Beer;
import guru.springframework.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

//@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final String BEER_1_UPC = "0631234200036";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count() == 0){

            beerRepository.save(Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .quantityToBrew(200)
                .minOnHand(45)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("20.00"))
                .build());

            beerRepository.save(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .quantityToBrew(100)
                .minOnHand(85)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("10.00"))
                .build());

            beerRepository.save(Beer.builder()
                  .beerName("No Hammers")
                  .beerStyle("Pale Ale")
                  .quantityToBrew(500)
                  .minOnHand(8)
                  .upc(BEER_3_UPC)
                  .price(new BigDecimal("10.00"))
                  .build());
        }
    }
}
