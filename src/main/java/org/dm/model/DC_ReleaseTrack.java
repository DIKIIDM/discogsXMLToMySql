package org.dm.model;

import org.dm.Core;

public class DC_ReleaseTrack {
    public Integer id;
    public Integer idRelease;
    public Integer idReleaseDC;
    public String sTitle;
    public String sType;
    public String sPosition;
    public String sDuration;
    //----------------------------------------------------------------------------------
    public DC_ReleaseTrack() {

    }
    //----------------------------------------------------------------------------------
    public void setsTitle(String sValue) {
        if (sValue != null)
            this.sTitle = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sTitle = null;
    }
    //----------------------------------------------------------------------------------
    public void setsType(String sValue) {
        if (sValue != null)
            this.sType = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sType = null;
    }
    //----------------------------------------------------------------------------------
    public void setsPosition(String sValue) {
        if (sValue != null)
            this.sPosition = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sPosition = null;
    }
    //----------------------------------------------------------------------------------
    public void setsDuration(String sValue) {
        if (sValue != null)
            this.sDuration = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sDuration = null;
    }
}


