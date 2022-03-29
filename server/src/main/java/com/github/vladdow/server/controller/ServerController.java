package com.github.vladdow.server.controller;

import com.github.vladdow.server.service.CardService;

import com.github.vladdow.dto.ClientDTO;
import com.github.vladdow.dto.BalanceDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class ServerController {

    private CardService service;

    @GetMapping("/server/{cardNumber}")
    public ClientDTO getClient(@PathVariable BigDecimal cardNumber) {
        return service.getClientByNumberCard(cardNumber);
    }

    @GetMapping("/server/{cardNumber}/balance")
    public BalanceDTO getBalance(@PathVariable BigDecimal cardNumber) {
        return service.getBalanceByNumberCard(cardNumber);
    }

}
