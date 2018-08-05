package br.gabrielspassos.poc.mongopoc.repositories;

import br.gabrielspassos.poc.mongopoc.entities.CardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<CardEntity, String> {

    CardEntity findByNumber(String number);
    List<CardEntity> findAll();
}
