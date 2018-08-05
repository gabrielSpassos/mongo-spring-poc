package br.gabrielspassos.poc.mongopoc.controllers;

import br.gabrielspassos.poc.mongopoc.controllers.dto.CardDto;
import br.gabrielspassos.poc.mongopoc.controllers.dto.SaveCardDto;
import br.gabrielspassos.poc.mongopoc.entities.CardEntity;
import br.gabrielspassos.poc.mongopoc.services.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(
            value="Get all cards",
            response=CardDto.class,
            notes="This operation return a list of cards")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Return a list of cards",
                    response=CardDto.class
            )
    })
    @GetMapping(value = "/cards")
    public ResponseEntity<?> getCards(){
        List<CardDto> cards = cardService.getCards().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ok(cards);
    }

    @ApiOperation(
            value="Save card",
            response=CardDto.class,
            notes="This operation save a card")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Return the saved card",
                    response=CardDto.class
            )
    })
    @PostMapping(value = "/cards")
    public ResponseEntity<?> saveCard(@RequestBody SaveCardDto saveCardDto){
        return Stream.of(saveCardDto)
                .map(this::convertToEntity)
                .map(entity -> cardService.saveCard(entity))
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .findAny()
                .get();
    }

    private CardDto convertToDto(CardEntity cardEntity){
        return modelMapper.map(cardEntity, CardDto.class);
    }

    private CardEntity convertToEntity(SaveCardDto saveCardDto){
        return modelMapper.map(saveCardDto, CardEntity.class);
    }
}
