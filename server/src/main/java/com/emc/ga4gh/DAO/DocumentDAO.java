package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.OEntity;

/**
 * Created by liza on 12.04.15.
 */
public interface DocumentDAO<T extends OEntity> extends CrudDAO<T, String> {
    @Override
    @OTransacrional
    String create(T newInstance);

    @Override
    @OTransacrional
    T read(String id);

    @Override
    @OTransacrional
    void update(T transientObject);

    @Override
    @OTransacrional
    void delete(T persistentObject);
}
