package com.github.vladdow.server.service;

import com.github.vladdow.server.repository.CardRepository;

import com.github.vladdow.dto.BalanceDTO;
import com.github.vladdow.dto.ClientDTO;

import com.github.vladdow.server.entity.Card;
import com.github.vladdow.server.entity.Client;
import com.github.vladdow.server.entity.Account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTest {

    private final String fullName = "Давыдов Тимур Богданович";

    private final BigDecimal accountNumber = new BigDecimal("40817810570000123498");
    private final BigDecimal accountBalance = new BigDecimal("20000");

    private final BigDecimal cardNumber = new BigDecimal("4539804056541925");
    private final int PIN = 4415;

    private final ClientDTO clientTest = new ClientDTO(fullName, accountNumber, accountBalance);
    private final BalanceDTO balanceTest = new BalanceDTO(accountBalance);

    @MockBean
    private CardRepository repository;

    @Autowired
    private CardService service;

    @BeforeEach
    public void init() {
        Client client = new Client(0L, fullName, new ArrayList<>());
        Account account = new Account(0L, accountNumber, accountBalance, client, new ArrayList<>());
        client.getAccounts().add(account);
        Card card = new Card(0L, cardNumber, PIN, account);
        account.getCards().add(card);
        when(repository.findByNumber(cardNumber)).thenReturn(Optional.of(card));
    }

    @Test
    public void getClientTest() {
        ClientDTO client = service.getClientByNumberCard(cardNumber, PIN);
        Assertions.assertEquals(clientTest, client);
    }

    @Test
    public void getBalanceTest() {
        BalanceDTO balance = service.getBalanceByNumberCard(cardNumber, PIN);
        Assertions.assertEquals(balanceTest, balance);
    }

    @Test
    public void exceptionTest() {
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class,
                () -> service.getClientByNumberCard(cardNumber, 4413)
        );
        Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
    }

}
