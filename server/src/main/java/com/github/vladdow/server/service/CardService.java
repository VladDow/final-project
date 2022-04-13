package com.github.vladdow.server.service;

import com.github.vladdow.server.entity.Card;

import com.github.vladdow.server.repository.CardRepository;

import com.github.vladdow.dto.BalanceDTO;
import com.github.vladdow.dto.ClientDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class CardService {

    private CardRepository repository;

    public ClientDTO getClientByNumberCard(BigDecimal cardNumber, int pin) {
        Card card = cardValidation(cardNumber, pin);
        return new ClientDTO(
                card.getAccount().getClient().getFullName(),
                card.getAccount().getNumber(),
                card.getAccount().getBalance()
        );
    }

    public BalanceDTO getBalanceByNumberCard(BigDecimal cardNumber, int pin) {
        Card card = cardValidation(cardNumber, pin);
        return new BalanceDTO(card.getAccount().getBalance());
    }

    private Card cardValidation(BigDecimal cardNumber, int pin) {
        Card card = repository.findByNumber(cardNumber).orElseThrow(() -> {
            log.info("Invalid card number");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid card number");
        });
        if (card.getPin() != pin) {
            log.info("Invalid PIN");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid PIN");
        }
        return card;
    }

}
