package com.emc.ga4gh.DTO;

/**
 * Created by liza on 15.04.15.
 */
public class Reference implements Entity {

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
}
