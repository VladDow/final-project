package com.github.vladdow.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String fullName;
    private BigDecimal accountNumber;
    private BigDecimal balance;
}
