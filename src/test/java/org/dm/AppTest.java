package org.dm;

import static org.junit.Assert.assertTrue;

import org.apache.commons.cli.ParseException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AppTest{
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    //------------------------------------------------------------------
    @Test
    public void wrongArgs() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Wrong arguments");
        App.xmlToMysql(new String[]{"", ""});
    }
    //------------------------------------------------------------------
    @Test
    public void noArgs() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Wrong arguments");
        App.xmlToMysql(new String[]{});
    }
    //------------------------------------------------------------------
    @Test
    public void wrongPathToFolder() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Wrong path to the folder");
        App.xmlToMysql(new String[]{"-f", "C:/123/321/123", "-u", "", "-p", "", "-url", ""});
    }
    //------------------------------------------------------------------
    @Test
    public void wrongDBParams() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Connection to database error");
        App.xmlToMysql(new String[]{
                 "-f","C:/dm/work/dz/discogs"
                ,"-u", "root"
                ,"-p", "root!!"
                ,"-url", "jdbc:mysql://localhost/discogs?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC"}
        );
    }
    //------------------------------------------------------------------
    @Test
    public void xmlFilesNotFound() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("XML files not found");
        App.xmlToMysql(new String[]{
                 "-f","C:/dm/work/dz/discogs/empty"
                ,"-u", "root"
                ,"-p", "root"
                ,"-url", "jdbc:mysql://localhost/discogs?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC"}
        );
    }
    //------------------------------------------------------------------
    @Ignore
    @Test
    public void xmlFileArtistNotFound() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("XML files not found");
        App.xmlToMysql(new String[]{
                "-f","C:/dm/work/dz/discogs"
                ,"-u", "root"
                ,"-p", "root"
                ,"-url", "jdbc:mysql://localhost/discogs?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC"}
        );
    }
}
