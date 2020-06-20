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

}
