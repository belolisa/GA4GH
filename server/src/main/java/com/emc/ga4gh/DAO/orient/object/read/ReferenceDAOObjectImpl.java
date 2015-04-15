package com.emc.ga4gh.DAO.orient.object.read;

import com.emc.ga4gh.DAO.ReferenceDAO;
import com.emc.ga4gh.DAO.orient.object.AbstractObjectDAO;
import com.emc.ga4gh.DTO.Reference;
import org.springframework.stereotype.Repository;

/**
 * Created by liza on 12.04.15.
 */
@Repository
public class ReferenceDAOObjectImpl extends AbstractObjectDAO<Reference> implements ReferenceDAO {

    @Override
    protected String getEntityName() {
        return "Reference";
    }
}
