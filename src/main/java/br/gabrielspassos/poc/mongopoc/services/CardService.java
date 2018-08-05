package br.gabrielspassos.poc.mongopoc.services;

import br.gabrielspassos.poc.mongopoc.entities.CardEntity;
import br.gabrielspassos.poc.mongopoc.repositories.CardRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CardEntity saveCard(CardEntity cardEntity) {
        try {
            cardEntity.setNumber(createCardNumber());
            return cardRepository.save(cardEntity);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to save card");
        }
    }

    private String createCardNumber() {
        return RandomStringUtils.randomNumeric(5,5);
    }
}
