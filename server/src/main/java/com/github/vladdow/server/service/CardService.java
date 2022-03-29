package com.github.vladdow.server.service;

import com.github.vladdow.server.entity.Card;

import com.github.vladdow.server.repository.CardRepository;

import com.github.vladdow.dto.BalanceDTO;
import com.github.vladdow.dto.ClientDTO;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CardService {

    private CardRepository repository;

    public ClientDTO getClientByNumberCard(BigDecimal cardNumber) {
        Card card = repository.findByNumber(cardNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid card number"));
        return new ClientDTO(
                card.getAccount().getClient().getFullName(),
                card.getAccount().getNumber(),
                card.getAccount().getBalance()
        );
    }

    public BalanceDTO getBalanceByNumberCard(BigDecimal cardNumber) {
        Card card = repository.findByNumber(cardNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid card number"));
        return new BalanceDTO(card.getAccount().getBalance());
    }

}
