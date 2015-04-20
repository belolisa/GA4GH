package com.emc.ga4gh;

import com.emc.ga4gh.indexing.read.OrientReadIndexer;
import com.emc.ga4gh.indexing.reference.OrientReferenceIndexer;
import com.emc.ga4gh.indexing.variant.OrientVariantIndexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UploadController {

    @Autowired
    private OrientReadIndexer readIndexer;

    @Autowired
    private OrientReferenceIndexer referenceIndexer;

    @Autowired
    private OrientVariantIndexer variantIndexer;

    public static void main(String[] args) {
        ApplicationContext context  = new FileSystemXmlApplicationContext("classpath*:cliContext.xml");
        UploadController bean = context.getBean(UploadController.class);

        String fileType = args[0];
        String fullPath = args[1];

        FileType type = null;
        try {
            type = FileType.getType(fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert type != null;
        switch (type) {
            case BAM: case SAM:
                bean.readIndexer.index(fullPath);
                break;
            case VCF:
                bean.variantIndexer.index(fullPath);
                break;
            case SATA:
                bean.referenceIndexer.index(fullPath);
                break;
        }
    }
}
