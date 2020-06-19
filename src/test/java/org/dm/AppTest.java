package org.dm;

import static org.junit.Assert.assertTrue;

import org.apache.commons.cli.ParseException;
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
}
