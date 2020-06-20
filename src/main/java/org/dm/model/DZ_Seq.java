package org.dm.model;

public class DZ_Seq {
    private int num;
    //----------------------------------------------------------------------------------
    public DZ_Seq() {
        num = 0;
    }
    //----------------------------------------------------------------------------------
    public int getNext() {
        return num++;
    }
}
