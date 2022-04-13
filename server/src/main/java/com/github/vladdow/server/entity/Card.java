package com.github.vladdow.server.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cards")
public class Card {

    private @Id @GeneratedValue Long id;

    private BigDecimal number;
    private int pin;

    @ManyToOne
    private Account account;

}
