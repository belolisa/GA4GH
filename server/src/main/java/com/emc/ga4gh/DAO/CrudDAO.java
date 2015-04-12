package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.OEntity;

/**
 * Created by Elizaveta Belokopytova.
 */

public interface CrudDAO<T extends OEntity> {

    String create(T newInstance);

    T read(String id);

    void update(T transientObject);

    void delete(T persistentObject);

}
