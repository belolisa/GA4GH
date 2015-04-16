package com.emc.ga4gh.DAO.orient.object;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DTO.Read;
import org.springframework.stereotype.Repository;

/**
 * Created by liza on 16.04.15.
 */

@Repository
public class ReadObjectDAO extends AbstractObjectDAO<Read> implements ReadDAO {

    @Override
    protected String getEntityName() {
        return "Read";
    }

}
