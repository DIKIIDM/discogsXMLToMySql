package org.dm.model;

public class DC_ArtistVariation {
    public Integer id;
    public Integer idArtist;
    public Integer idArtistDC;
    public String sName;
    public String sNameShort;
    //----------------------------------------------------------------------------------
    public DC_ArtistVariation() {

    }
    //----------------------------------------------------------------------------------
    public void setsName(String sName) {
        this.sName = sName.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");;
    }
    //----------------------------------------------------------------------------------
    public void init() {
        if (sName != null)
            this.sNameShort = sName.substring(0, Math.min(sName.length(), 254));
    }
}
