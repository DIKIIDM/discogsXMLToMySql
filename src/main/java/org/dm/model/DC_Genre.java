package org.dm.model;

import org.dm.Core;

public class DC_Genre extends DC_Entity {
    public String sName;
    //----------------------------------------------------------------------------------
    public void setsName(String sValue) {
        if (sValue != null)
            this.sName = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sName = null;
    }
}
