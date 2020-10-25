package guru.springframework.beerservice.events;

import guru.springframework.beerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5218034560706536178L;

    private BeerDto beerDto;
}
