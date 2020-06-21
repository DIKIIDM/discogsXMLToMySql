package org.dm;

public class Core {
    //-------------------------------------------------------------------------
    public static boolean isNull(String string) {
         boolean res;
         if (string == null)
             res = true;
         else if (string.equals(""))
             res = true;
         else
             res = false;
         return res;
    }
    //-------------------------------------------------------------------------
    public static String nvl(String value1, String value2) {
         return (!isNull(value1)? value1: value2);
    }
    //----------------------------------------------------------------------------------
    public static String truncate(String sValue, int max) {
        return sValue.substring(0, Math.min(sValue.length(), max));
    }
    //----------------------------------------------------------------------------------
    public static String replace4bytesChars(String sValue) {
        return sValue.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
    }
}
