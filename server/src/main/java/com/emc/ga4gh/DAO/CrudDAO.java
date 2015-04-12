package com.emc.ga4gh.DAO;

/**
 * Created by Elizaveta Belokopytova.
 */

public interface CrudDAO<T, PK> {

    PK create(T newInstance);

    T read(PK id);

    void update(T transientObject);

    void delete(T persistentObject);

}
