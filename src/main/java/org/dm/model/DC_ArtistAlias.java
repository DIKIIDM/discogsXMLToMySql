package org.dm.model;

public class DC_ArtistAlias extends DC_Entity {
    public Integer idArtist;
    public Integer idArtistDC;
    public String sName;
    //----------------------------------------------------------------------------------
    public DC_ArtistAlias() {

    }
    //----------------------------------------------------------------------------------
    public void setsName(String sName) {
        this.sName = sName.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
    }
}
