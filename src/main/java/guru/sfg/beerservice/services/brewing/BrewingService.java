package guru.sfg.beerservice.services.brewing;

import guru.sfg.beerservice.config.JmsConfig;
import guru.sfg.beerservice.domain.Beer;
import guru.sfg.beerservice.repositories.BeerRepository;
import guru.sfg.beerservice.services.inventory.BeerInventoryService;
import guru.sfg.beerservice.web.mappers.BeerMapper;
import guru.sfg.common.events.BrewBeerEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() throws JmsException {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getQuantityOnHand(beer.getId());

            log.debug("Min QOH is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if(beer.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
