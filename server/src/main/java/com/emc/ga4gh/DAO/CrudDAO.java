package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.Entity;

import java.util.Optional;

/**
 * Created by Elizaveta Belokopytova.
 */

public interface CrudDAO<T extends Entity> {

    T create(T newInstance);

    Optional<T> read(String id);

    void update(T transientObject);

    void delete(T persistentObject);

}
