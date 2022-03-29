package com.github.vladdow.server.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Cards")
public class Card {

    private @Id @GeneratedValue Long id;

    private BigDecimal number;

    @ManyToOne
    private Account account;

}
