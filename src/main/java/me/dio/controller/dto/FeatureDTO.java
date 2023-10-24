package me.dio.controller.dto;

import me.dio.domain.model.Feature;

public record FeatureDTO(Long id, String icon, String description) {

    public FeatureDTO(Feature model) {
        this(model.getId(), model.getIcon(), model.getDescription());
    }

    public Feature toModel() {
        Feature model = new Feature();
        model.setId(this.id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}
