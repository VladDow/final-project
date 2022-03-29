package com.github.vladdow.client.controller;

import com.github.vladdow.dto.BalanceDTO;
import com.github.vladdow.dto.ClientDTO;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
public class ClientController {

    private static final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ATM/{cardNumber}")
    public ClientDTO getClient(@PathVariable BigDecimal cardNumber) {
        try {
            return restTemplate.getForObject("http://127.0.0.1:8085/server/{cardNumber}", ClientDTO.class, cardNumber);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid card number");
        }
    }

    @GetMapping("/ATM/{cardNumber}/balance")
    public BalanceDTO getBalance(@PathVariable BigDecimal cardNumber) {
        try {
            return restTemplate.getForObject("http://127.0.0.1:8085/server/{cardNumber}/balance", BalanceDTO.class, cardNumber);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid card number");
        }
    }

}
