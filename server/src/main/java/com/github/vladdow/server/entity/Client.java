package com.github.vladdow.server.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clients")
public class Client {

    private @Id @GeneratedValue Long id;

    private String fullName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

}
