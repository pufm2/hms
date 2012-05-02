package puf.m2.hms.model;

//TODO There is no test in this class!!! (which class do you to test here?)

public abstract class TestSupport {
    private static File dbFile = new File("HMS.db3");
    private static File dbBackupFile = new File("HMS.db3.bak");

    @BeforeClass
    public static void beforeClass() {
        HmsEntity.setCached(false);
    }

    @Before
    public void before() throws IOException {
        copyFile(dbFile, dbBackupFile);

    }

    @After
    public void after() {
        dbFile.delete();
        dbBackupFile.renameTo(dbFile);
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