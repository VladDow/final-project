package com.github.vladdow.client.controller;

import com.github.vladdow.dto.BalanceDTO;
import com.github.vladdow.dto.ClientDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(value = "/ATM")
public class ClientController {

    private final RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication("ATM1", "test").build();

    @GetMapping("/{cardNumber}/{pin}")
    public ClientDTO getClient(@PathVariable BigDecimal cardNumber, @PathVariable int pin) {
        try {
            log.info("The user used an ATM to get card details: " + cardNumber + "/" + pin);
            return restTemplate.getForObject("http://127.0.0.1:8085/server/{cardNumber}/{pin}", ClientDTO.class, cardNumber, pin);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect data");
        }
    }

    @GetMapping("/{cardNumber}/{pin}/balance")
    public BalanceDTO getBalance(@PathVariable BigDecimal cardNumber, @PathVariable int pin) {
        try {
            log.info("The user used an ATM to get card balance: " + cardNumber + "/" + pin);
            return restTemplate.getForObject("http://127.0.0.1:8085/server/{cardNumber}/{pin}/balance", BalanceDTO.class, cardNumber, pin);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect data");
        }
    }

}
