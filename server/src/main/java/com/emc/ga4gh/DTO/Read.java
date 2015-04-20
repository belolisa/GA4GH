package com.emc.ga4gh.DTO;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Elizaveta Belokopytova.
 */
public class Read implements Entity {

    @Version
    private Integer version = 0;

    @Id
    private String rid;

    private String path;

    private String readGroupId;

    private Integer alignmentStart;

    private Integer alignmentEnd;

    private int numberInFile;

    private String referenceName;

    private Integer referenceId;

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
                "version=" + version +
                ", rid='" + rid + '\'' +
                ", path='" + path + '\'' +
                ", readGroupId='" + readGroupId + '\'' +
                ", alignmentStart=" + alignmentStart +
                ", alignmentEnd=" + alignmentEnd +
                '}';
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setReadGroupId(String readGroupId) {
        this.readGroupId = readGroupId;
    }

    public String getReadGroupId() {
        return readGroupId;
    }

    public void setAlignmentStart(Integer alignmentStart) {
        this.alignmentStart = alignmentStart;
    }

    public Integer getAlignmentStart() {
        return alignmentStart;
    }

    public void setAlignmentEnd(Integer alignmentEnd) {
        this.alignmentEnd = alignmentEnd;
    }

    public Integer getAlignmentEnd() {
        return alignmentEnd;
    }

    public void setNumberInFile(int numberInFile) {
        this.numberInFile = numberInFile;
    }

    public int getNumberInFile() {
        return numberInFile;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getReferenceId() {
        return referenceId;
    }
}
