package me.dio.controller.dto;

import me.dio.domain.model.User;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record UserDTO(Long id, String name, AccountDTO account, CreditCardDTO creditCard, List<HintDTO> hints, List<FeatureDTO> features) {
    public UserDTO(User model) {
        this(
                model.getId(),
                model.getName(),
                ofNullable(model.getAccount()).map(AccountDTO::new).orElse(null),
                ofNullable(model.getCreditCard()).map(CreditCardDTO::new).orElse(null),
                ofNullable(model.getHints()).orElse(emptyList()).stream().map(HintDTO::new).collect(toList()),
                ofNullable(model.getFeatures()).orElse(emptyList()).stream().map(FeatureDTO::new).collect(toList())
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setAccount(ofNullable(this.account).map(AccountDTO::toModel).orElse(null));
        model.setCreditCard(ofNullable(this.creditCard).map(CreditCardDTO::toModel).orElse(null));
        model.setFeatures(ofNullable(this.features).orElse(emptyList()).stream().map(FeatureDTO::toModel).collect(toList()));
        model.setHints(ofNullable(this.hints).orElse(emptyList()).stream().map(HintDTO::toModel).collect(toList()));
        return model;
    }
}
