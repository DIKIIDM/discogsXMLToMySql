package org.dm.model;

import org.dm.Core;

public class DC_Label extends DC_Entity {
    public String sName;
    //----------------------------------------------------------------------------------
    public DC_Label() {

    }
    //----------------------------------------------------------------------------------
    public void setsName(String sValue) {
        if (sValue != null)
            this.sName = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sName = null;
    }
}


