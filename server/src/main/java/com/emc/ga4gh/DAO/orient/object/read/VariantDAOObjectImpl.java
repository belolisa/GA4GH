package com.emc.ga4gh.DAO.orient.object.read;

import com.emc.ga4gh.DAO.VariantDAO;
import com.emc.ga4gh.DAO.orient.object.AbstractObjectDAO;
import com.emc.ga4gh.DTO.Variant;
import org.springframework.stereotype.Repository;

/**
 * Created by liza on 12.04.15.
 */
@Repository
public class VariantDAOObjectImpl extends AbstractObjectDAO<Variant> implements VariantDAO {

    @Override
    protected String getEntityName() {
        return "Variant";
    }
}
