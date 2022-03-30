package com.github.vladdow.server.controller;

import com.github.vladdow.server.service.CardService;

import com.github.vladdow.dto.ClientDTO;
import com.github.vladdow.dto.BalanceDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/server")
public class ServerController {

    private CardService service;

    @GetMapping("/{cardNumber}/{pin}")
    public ClientDTO getClient(@PathVariable BigDecimal cardNumber, @PathVariable int pin) {
        log.info("User data request: Card number - " + cardNumber + " PIN - " + pin);
        return service.getClientByNumberCard(cardNumber, pin);
    }

    @GetMapping("/{cardNumber}/{pin}/balance")
    public BalanceDTO getBalance(@PathVariable BigDecimal cardNumber, @PathVariable int pin) {
        log.info("User balance request: Card number - " + cardNumber + " PIN - " + pin);
        return service.getBalanceByNumberCard(cardNumber, pin);
    }

}
