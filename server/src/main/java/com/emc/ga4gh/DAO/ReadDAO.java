package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DAO.builder.SQLBuilder;
import com.emc.ga4gh.DTO.Read;

import java.util.List;

/**
 * Created by liza on 12.04.15.
 */

public interface ReadDAO extends CrudSelectDAO<Read> {

    List<Read> findIncOrdered(String referenceId, String referenceName, Long start, Long end, List<String> readGroupIds);

}
