package guru.springframework.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.beerservice.bootstrap.BeerLoader;
import guru.springframework.beerservice.services.BeerService;
import guru.springframework.beerservice.web.model.BeerDto;
import guru.springframework.beerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.guru", uriPort = 80)
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception{
        given(beerService.getById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("isCold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                                pathParameters(parameterWithName("beerId").description("UUID of the desired beer to get.")
                                ),
                                requestParameters(
                                        parameterWithName("isCold").description("Is Beer Cold Query Param")
                                ),
                                responseFields(
                                        fieldWithPath("beerId").description("Id of Beer"),
                                        fieldWithPath("version").description("Version number"),
                                        fieldWithPath("createdDate").description("Date Created"),
                                        fieldWithPath("lastModifiedDate").description("Date Updated"),
                                        fieldWithPath("beerName").description("Name of Beer"),
                                        fieldWithPath("beerStyle").description("Beer Style"),
                                        fieldWithPath("upc").description("UPC of Beer"),
                                        fieldWithPath("price").description("price of Beer"),
                                        fieldWithPath("quantityOnHand").description("Quantity on Hand")
                                )
                ));
    }

    @Test
    void saveNewBeer() throws Exception{
        given(beerService.getById(any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-post",
                                requestFields(
                                        fields.withPath("beerId").ignored(),
                                        fields.withPath("version").ignored(),
                                        fields.withPath("createdDate").ignored(),
                                        fields.withPath("lastModifiedDate").ignored(),
                                        fields.withPath("beerName").description("Name of Beer"),
                                        fields.withPath("beerStyle").description("Beer Style"),
                                        fields.withPath("upc").description("UPC of Beer"),
                                        fields.withPath("price").description("price of Beer"),
                                        fields.withPath("quantityOnHand").ignored()
                                )));
    }

    @Test
    void updateBeerById() throws Exception {


        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(put("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent())
                .andDo(document("v1/beer-put",
                                requestFields(
                                        fields.withPath("beerId").ignored(),
                                        fields.withPath("version").ignored(),
                                        fields.withPath("createdDate").ignored(),
                                        fields.withPath("lastModifiedDate").ignored(),
                                        fields.withPath("beerName").description("Name of Beer"),
                                        fields.withPath("beerStyle").description("Beer Style"),
                                        fields.withPath("upc").description("UPC of Beer"),
                                        fields.withPath("price").description("price of Beer"),
                                        fields.withPath("quantityOnHand").ignored()
                                )));
    }

    BeerDto getValidBeerDto(){

        return BeerDto.builder()
                .beerName("Castle")
                .beerStyle(BeerStyleEnum.GOSE)
                .price(new BigDecimal("10.00"))
                .upc(BeerLoader.BEER_2_UPC)
                .build();
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}
