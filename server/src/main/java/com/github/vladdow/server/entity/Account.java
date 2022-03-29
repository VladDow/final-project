package com.github.vladdow.server.entity;

import java.util.List;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Accounts")
public class Account {

    private @Id @GeneratedValue Long id;

    private BigDecimal number;
    private BigDecimal balance;

    @ManyToOne
    private Client client;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "account")
    private List<Card> cards;

}
