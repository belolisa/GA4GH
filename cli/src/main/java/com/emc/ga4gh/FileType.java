package com.emc.ga4gh;

/**
 * Created by elizaveta on 19.04.2015.
 */
public enum FileType {

    SAM("sam"),
    BAM("bam"),
    VCF("vcf"),
    SATA("sata");

    private String typeValue;

    FileType(String type) {
        typeValue = type;
    }

    static public FileType getType(String pType) {
        for (FileType type : FileType.values()) {
            if (type.getTypeValue().equals(pType)) {
                return type;
            }
        }
        throw new RuntimeException("unknown type");
    }

    public String getTypeValue() {
        return typeValue;
    }
}
