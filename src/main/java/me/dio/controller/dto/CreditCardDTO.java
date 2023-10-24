package me.dio.controller.dto;

import me.dio.domain.model.CreditCard;

import java.math.BigDecimal;

public record CreditCardDTO(Long id, String number, BigDecimal limit) {

    public CreditCardDTO(CreditCard model) {
        this(
                model.getId(),
                model.getNumber(),
                model.getLimit()
        );
    }

    public CreditCard toModel() {
        CreditCard model = new CreditCard();
        model.setId(this.id);
        model.setLimit(this.limit);
        model.setNumber(this.number);
        return model;
    }
}
