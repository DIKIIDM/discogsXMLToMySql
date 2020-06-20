package org.dm.model;

public class DC_ArtistAlias {
    public Integer id;
    public Integer idArtist;
    public Integer idArtistDC;
    public Integer idDC;
    public String sName;
    //----------------------------------------------------------------------------------
    public DC_ArtistAlias() {

    }
    //----------------------------------------------------------------------------------
    public void setsName(String sName) {
        this.sName = sName.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");;
    }
}
