package utils;

import java.io.File;

public class Consts {

    public static final String PASSED = "[PASSED]";
    public static final String FAILED = "[FAILED]";
    public static final String SKIPPED = "[SKIPPED]";


    private static String fs = File.separator;
    private static String resourcesPath = fs+"src"+fs+"main"+fs+"resources"+fs;

    public static final String PROJECT_SCREENSHOT_PATH = System.getProperty("user.dir")+resourcesPath+"screenshots"+fs;

}
