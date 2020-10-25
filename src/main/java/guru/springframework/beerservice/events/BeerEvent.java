package guru.springframework.beerservice.events;

import guru.springframework.beerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5218034560706536178L;

    private final BeerDto beerDto;
}
