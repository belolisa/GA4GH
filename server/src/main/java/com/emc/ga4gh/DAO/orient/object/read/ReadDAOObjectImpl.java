package com.emc.ga4gh.DAO.orient.object.read;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DAO.orient.object.AbstractObjectDAO;
import com.emc.ga4gh.DTO.Read;
import org.springframework.stereotype.Repository;

/**
 * Created by liza on 12.04.15.
 */
@Repository
public class ReadDAOObjectImpl extends AbstractObjectDAO<Read> implements ReadDAO {

    @Override
    protected String getEntityName() {
        return "Read";
    }
}
