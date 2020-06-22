package org.dm.model;

import org.dm.Core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DC_Release extends DC_Entity {
    public Integer nYear;
    public String sTitle;
    public String sReleased;
    public String sCountry;
    public String sLabel;
    public List<DC_ReleaseArtist> lArtist;
    public List<DC_ReleaseArtist> lExtraArtist;
    public List<DC_ReleaseStyle> lStyle;
    public List<DC_ReleaseGenre> lGenre;
    public List<DC_ReleaseTrack> lTrack;
    //----------------------------------------------------------------------------------
    public DC_Release() {
        lArtist = new ArrayList<>();
        lExtraArtist = new ArrayList<>();
        lStyle = new ArrayList<>();
        lGenre = new ArrayList<>();
        lTrack = new ArrayList<>();
    }
    //----------------------------------------------------------------------------------
    public void setsTitle(String sValue) {
        if (sValue != null)
            this.sTitle = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sTitle = null;
    }
    //----------------------------------------------------------------------------------
    public void setsCountry(String sValue) {
        if (sValue != null)
            this.sCountry = Core.truncate(Core.replace4bytesChars(sValue), 100);
        else
            this.sCountry = null;
    }
    //----------------------------------------------------------------------------------
    public void setsLabel(String sValue) {
        if (sValue != null)
            this.sLabel = Core.truncate(Core.replace4bytesChars(sValue), 1000);
        else
            this.sLabel = null;
    }
    //----------------------------------------------------------------------------------
    public void setsReleased(String sReleased) {
        this.sReleased = sReleased;
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher matcher = pattern.matcher(this.sReleased);
        if (matcher.find()) {
            this.setnYear(Integer.valueOf(matcher.group(1)));
        }
    }
    //----------------------------------------------------------------------------------
    public void setnYear(Integer nYear) {
        this.nYear = nYear;
    }
}


