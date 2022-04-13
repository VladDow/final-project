package com.github.vladdow.server.repository;

import com.github.vladdow.server.entity.Card;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findByNumber(BigDecimal number);
}
