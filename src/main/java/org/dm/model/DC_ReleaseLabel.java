package org.dm.model;

import org.dm.Core;

public class DC_ReleaseLabel extends DC_Entity {
    public Integer idRelease;
    public Integer idReleaseDC;
    public Integer idLabel;
    public Integer idLabelDC;
    public String sCatno;
    //----------------------------------------------------------------------------------
    public DC_ReleaseLabel() {

    }
    //----------------------------------------------------------------------------------
    public void setIdRelease(Integer value) {
        this.idRelease = value;
    }
    //----------------------------------------------------------------------------------
    public void setIdLabel(Integer value) {
        this.idLabel = value;
    }
    //----------------------------------------------------------------------------------
    public void setsCatno(String value) {
        if (value != null)
            this.sCatno = Core.truncate(Core.replace4bytesChars(value), 254);
        else
            this.sCatno = null;
    }
}
