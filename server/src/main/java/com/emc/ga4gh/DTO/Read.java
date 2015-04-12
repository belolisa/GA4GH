package com.emc.ga4gh.DTO;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Elizaveta Belokopytova.
 */
public class Read implements OEntity {

    @Version
    private int version;

    @Id
    private String rid;

    private String path;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getRid() {
        return rid;
    }

    @Override
    public void setRid(String rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "Read{" +
                "rid='" + rid + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
