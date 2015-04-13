package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.Entity;

/**
 * Created by Elizaveta Belokopytova.
 */

public interface CrudDAO<T extends Entity> {

    T create(T newInstance);

    T read(String id);

    void update(T transientObject);

    void delete(T persistentObject);

}
