package me.dio.controller.dto;

import me.dio.domain.model.Hint;

public record HintDTO(Long id, String icon, String description) {

    public HintDTO(Hint model) {
        this(model.getId(), model.getIcon(), model.getDescription());
    }

    public Hint toModel() {
        Hint model = new Hint();
        model.setId(this.id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}
