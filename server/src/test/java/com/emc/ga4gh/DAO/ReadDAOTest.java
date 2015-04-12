package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.Read;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class ReadDAOTest {

    @Autowired
    private ReadDAO rd;

    @Autowired
    private OObjectDatabaseTx db;

    @Test
    public void testCreate() throws Exception {
        Read read = new Read();
        read.setPath("/path");
        System.out.println("db = " + db);
//        System.out.println("rd.create(new Read()) = " + rd.create(read));
    }
}