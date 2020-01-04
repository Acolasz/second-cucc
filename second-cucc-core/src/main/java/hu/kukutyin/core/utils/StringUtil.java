package hu.kukutyin.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class StringUtil {

    private static final Logger logger = Logger.getLogger(StringUtil.class);

    public static String[] objectToStringArray(Object[] objectList) {
        try {
            List<String> stringList = new ArrayList<String>();
            for (int i = 0; i < objectList.length; i++) {
                try {
                    Object object = objectList[i];
                    stringList.add(object != null ? object.toString() : "null");
                } catch (Exception e) {
                    logger.debug(e);
                }
            }
            String[] strarray = new String[stringList.size()];
            stringList.toArray(strarray);
            return strarray;
        } catch (Exception e) {
            logger.debug(e);
        }
        return new String[]{};
    }

    public static String inputStreamToString(InputStream is, String charSet) {
        Scanner s = null;

        if (charSet != null) {
            s = new Scanner(is, charSet).useDelimiter("\\A");
        } else {
            s = new Scanner(is).useDelimiter("\\A");
        }
        return s.hasNext() ? s.next() : "";
    }

    public static String inputStreamToString(InputStream is) {
        return inputStreamToString(is, null);
    }

    /**
     * Read and returns resource file as String
     * srcClass == getClass() in caller side
     * resFilename "/" and filename relative in folder src/main/resources
     */
    public static String getResource(Class srcClass, String resFilename, String charSet) {
        String ret = null;
        InputStream input = srcClass.getResourceAsStream(resFilename);

        if (input != null) {
            ret = inputStreamToString(input, charSet);
        } else {
            logger.error("unable to read resource file: " + resFilename);
        }
        return ret;
    }

    public static String getResource(Class srcClass, String resFilename) {
        return getResource(srcClass, resFilename, null);
    }

    public static String substringBeforeLastStringFromBeforeLastSeparator(String str, String separator) {
        return StringUtils.substringAfterLast(StringUtils.substringBeforeLast(str, separator), separator);
    }


}
