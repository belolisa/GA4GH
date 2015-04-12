package com.emc.ga4gh;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Elizaveta Belokopytova.
 */

@Component
@Qualifier("main")
public class Main {

    @Autowired
    private OObjectDatabaseTx db;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
//        Main main = context.getBean(Main.class);
        context.getBean(OObjectDatabaseTx.class);
        Main main = (Main) context.getBean("main");
        OObjectDatabaseTx db = main.getDb();
        System.out.println("tx.getURL() = " + db.getURL());
        System.out.println("tx.getUser().getName() = " + db.getUser().getName());
        System.out.println("tx.getUser().getPassword() = " + db.getUser().getPassword());
    }

    public OObjectDatabaseTx getDb() {
        return db;
    }
}
