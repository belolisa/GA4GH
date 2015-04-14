package com.emc.ga4gh.DTO;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Elizaveta Belokopytova.
 */
public class Read implements Entity {

    @Version
    private Integer version;

    @Id
    private String rid;

    private String path;
    private String readGroupId;
    private int alignmentStart;
    private int alignmentEnd;
    private String alignmentSequence;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setReadGroupId(String readGroupId) {
        this.readGroupId = readGroupId;
    }

    public String getReadGroupId() {
        return readGroupId;
    }

    public void setAlignmentStart(int alignmentStart) {
        this.alignmentStart = alignmentStart;
    }

    public int getAlignmentStart() {
        return alignmentStart;
    }

    public void setAlignmentEnd(int alignmentEnd) {
        this.alignmentEnd = alignmentEnd;
    }

    public int getAlignmentEnd() {
        return alignmentEnd;
    }

    public void setAlignmentSequence(String alignmentSequence) {
        this.alignmentSequence = alignmentSequence;
    }

    public String getAlignmentSequence() {
        return alignmentSequence;
    }

}
