package org.dm.model;

import org.dm.Core;

public class DC_ReleaseFormat extends DC_Entity {
    public Integer idRelease;
    public Integer idReleaseDC;
    public Integer idFormat;
    public String sText;
    public Integer nQty;
    //----------------------------------------------------------------------------------
    public DC_ReleaseFormat() {

    }
    //----------------------------------------------------------------------------------
    public void setsText(String sValue) {
        if (sValue != null)
            this.sText = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sText = null;
    }
}


