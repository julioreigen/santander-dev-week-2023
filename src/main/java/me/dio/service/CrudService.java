package me.dio.service;

import java.util.List;

public interface CrudService<ID, Entity> {
    Entity findById(ID id);
    List<Entity> findAll();
    Entity create(Entity entity);
    Entity update(ID id, Entity entity);
    void delete(ID id);
}
