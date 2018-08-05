package br.gabrielspassos.poc.mongopoc.services;

import br.gabrielspassos.poc.mongopoc.entities.CardEntity;
import br.gabrielspassos.poc.mongopoc.repositories.CardRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<CardEntity> getCards(){
        try {
            return cardRepository.findAll();
        }catch (Exception e){
            throw new IllegalStateException("Failed to get cards");
        }
    }

    public CardEntity getCardByNumber(String number) {
        try{
            return cardRepository.findByNumber(number);
        }catch (Exception e) {
            throw new IllegalStateException(String.format("Failed to get card number %s", number));
        }
    }

    public CardEntity saveCard(CardEntity cardEntity) {
        try {
            cardEntity.setNumber(createCardNumber());
            return cardRepository.save(cardEntity);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to save card");
        }
    }

    public CardEntity updateCard(String number, CardEntity cardEntity) {
        try {
            CardEntity card = getCardByNumber(number);
            card = updateCardNameAndType(cardEntity.getName(), cardEntity.getType(), card);
            return cardRepository.save(card);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to update card");
        }
    }

    private String createCardNumber() {
        return RandomStringUtils.randomNumeric(5,5);
    }

    private CardEntity updateCardNameAndType(String name, String type, CardEntity card){
        card.setName(name);
        card.setType(type);
        return card;
    }
}
