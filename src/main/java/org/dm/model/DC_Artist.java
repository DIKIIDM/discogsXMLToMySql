package org.dm.model;

import org.dm.Core;

import java.util.ArrayList;
import java.util.List;

public class DC_Artist {
    public Integer id;
    public Integer idDC;
    public String sName;
    public String sRealName;
    public String sNameShort;
    public List<DC_ArtistAlias> lAlias;
    public List<DC_ArtistVariation> lVariation;
    //----------------------------------------------------------------------------------
    public DC_Artist() {
        lAlias = new ArrayList<>();
        lVariation = new ArrayList<>();
    }
    //----------------------------------------------------------------------------------
    public void setsName(String sName) {
        this.sName = sName.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
    }
    //----------------------------------------------------------------------------------
    public void setsRealName(String sRealName) {
        this.sRealName = sRealName.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
    }
    //----------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "id=" + this.id + "; name=" + Core.nvl(this.sName, "") + "; real name=" + Core.nvl(this.sRealName, "");
    }
    //----------------------------------------------------------------------------------
    public void init() {
        if (sName != null)
            this.sNameShort = sName.substring(0, Math.min(sName.length(), 254));
    }
}
