package puf.m2.hms.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import puf.m2.hms.db.DatabaseFactory;
import puf.m2.hms.db.DbException;

public abstract class TestSupport {
    protected static File dbFile = new File("HMS-test.db3");
    protected static File dbBackupFile = new File("HMS-test.db3.bak");

    @BeforeClass
    public static void beforeClass() throws DbException {
        HmsEntity.DB = DatabaseFactory.createDatabase(DatabaseFactory.SQLITE_DRIVER,
                "jdbc:sqlite:HMS-test.db3");
        HmsEntity.setCached(false);
        HmsEntity.DB.createConnection();
    }
    
    @AfterClass
    public static void afterClass() throws DbException {
        HmsEntity.DB.closeConnection();
    }
    
    protected static void backupDb() throws IOException {
        copyFile(dbFile, dbBackupFile);

    }

    protected static void restoreDb() throws DbException {
    	HmsEntity.DB.closeConnection();
    	dbFile.delete();
        dbBackupFile.renameTo(dbFile);
        HmsEntity.DB.createConnection();
    }
    
    protected static void breakDb() throws DbException {
    	HmsEntity.DB.closeConnection();
        dbFile.renameTo(dbBackupFile);
        HmsEntity.DB.createConnection();
    }
    
    protected static void unbreakDb() throws DbException {
    	HmsEntity.DB.closeConnection();
        dbFile.delete();
        dbBackupFile.renameTo(dbFile);
        HmsEntity.DB.createConnection();
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

}
