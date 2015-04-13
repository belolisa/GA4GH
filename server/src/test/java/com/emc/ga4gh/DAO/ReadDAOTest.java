package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.Read;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class ReadDAOTest {

    @Autowired
    private ReadDAO rd;

    @Autowired
    OObjectDatabaseTx db;

    private Read read;

    private String defaultPath;

    @Before
    public void setUp() throws Exception {
        read = new Read();
        defaultPath = "/path";
        read.setPath(defaultPath);
    }

    @Test
    public void testCreate() throws Exception {
        read = rd.create(read);
        assertEquals(db.query(new OSQLSynchQuery<Read>("select from Read where @rid = " + read.getRid())).size(), 1);
    }

    @Test
    public void testCreateCorrectPath() throws Exception {
        read = rd.create(read);
        assertEquals(read.getPath(), defaultPath);
    }

    @Test
    public void testRead() throws Exception {
        read = rd.create(read);
        assertTrue(rd.read(read.getRid()).isPresent());
    }

    @Test
    public void testUpdate() throws Exception {
        read = rd.create(read);

        String customPath = "/customPath";
        read.setPath(customPath);
        rd.update(read);

        Optional<Read> readOptional = rd.read(read.getRid());
        assertEquals(readOptional.get().getPath(), customPath);
    }

    @Test
    public void testDelete() throws Exception {
        read = rd.create(read);

        rd.delete(read);

        assertFalse(rd.read(read.getRid()).isPresent());
    }

    @After
    public void tearDown() throws Exception {
        db.delete(new ORecordId(read.getRid()));
    }
}