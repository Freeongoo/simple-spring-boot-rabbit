package com.prod.rabbit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PriceExchange {
    String app;
    String baseCoin;
    String postCoin;
    BigDecimal price;
}
