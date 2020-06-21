package org.dm.model;

import org.dm.Core;

public class DC_ReleaseArtist {
    public Integer id;
    public Integer idArtistDC;
    public Integer idRelease;
    public Integer idReleaseDC;
    public Integer idTrack;
    public String sName;
    public String sAnv;
    public String sJoin;
    public String sRole;
    public String sTracks;
    //----------------------------------------------------------------------------------
    public DC_ReleaseArtist() {

    }
    //----------------------------------------------------------------------------------
    public void setsName(String sValue) {
        if (sValue != null)
            this.sName = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sName = null;
    }
    //----------------------------------------------------------------------------------
    public void setsAnv(String sValue) {
        if (sValue != null)
            this.sAnv = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sAnv = null;
    }
    //----------------------------------------------------------------------------------
    public void setsJoin(String sValue) {
        if (sValue != null)
            this.sJoin = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sJoin = null;
    }
    //----------------------------------------------------------------------------------
    public void setsRole(String sValue) {
        if (sValue != null)
            this.sRole = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sRole = null;
    }
    //----------------------------------------------------------------------------------
    public void setsTracks(String sValue) {
        if (sValue != null)
            this.sTracks = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sTracks = null;
    }
}


