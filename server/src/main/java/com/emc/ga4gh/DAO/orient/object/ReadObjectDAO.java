package com.emc.ga4gh.DAO.orient.object;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DAO.builder.SelectBuilder;
import com.emc.ga4gh.DTO.Read;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liza on 16.04.15.
 */

@Repository
public class ReadObjectDAO extends AbstractObjectDAO<Read> implements ReadDAO {

    @Override
    protected String getCollectionName() {
        return "Read";
    }
}
