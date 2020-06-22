package org.dm;


import org.dm.model.DC_Label;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ParserLabelTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    //------------------------------------------------------------------
    @Test
    public void parseArtists() throws Exception {
        String sXML_label = "" +
                "<labels>\n" +
                "    <label><images><image height=\"24\" type=\"primary\" uri=\"\" uri150=\"\" width=\"132\"/><image height=\"126\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"587\"/><image height=\"196\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/><image height=\"121\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"275\"/><image height=\"720\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"382\"/><image height=\"398\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"500\"/><image height=\"189\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/></images><id>1</id><name>Planet E</name><contactinfo>Planet E Communications&#13;\n" +
                "            P.O. Box 27218&#13;\n" +
                "            Detroit, Michigan, MI 48227&#13;\n" +
                "            USA&#13;\n" +
                "            &#13;\n" +
                "            Phone: +1 313 874 8729&#13;\n" +
                "            Fax: +1 313 874 8732&#13;\n" +
                "            Email: info@Planet-e.net</contactinfo><profile>[a=Carl Craig]'s classic techno label founded in 1991.&#13;\n" +
                "            &#13;\n" +
                "            On at least 1 release, Planet E is listed as publisher.</profile><data_quality>Correct</data_quality><urls><url>http://planet-e.net</url><url>http://planetecommunications.bandcamp.com</url><url>http://www.facebook.com/planetedetroit</url><url>http://www.flickr.com/photos/planetedetroit</url><url>http://plus.google.com/100841702106447505236</url><url>http://www.instagram.com/carlcraignet</url><url>http://myspace.com/planetecom</url><url>http://myspace.com/planetedetroit</url><url>http://soundcloud.com/planetedetroit</url><url>http://twitter.com/planetedetroit</url><url>http://vimeo.com/user1265384</url><url>http://en.wikipedia.org/wiki/Planet_E_Communications</url><url>http://www.youtube.com/user/planetedetroit</url></urls><sublabels><label id=\"86537\">Antidote (4)</label><label id=\"41841\">Community Projects</label><label id=\"153760\">Guilty Pleasures</label><label id=\"31405\">I Ner Zon Sounds</label><label id=\"277579\">Planet E Communications</label><label id=\"294738\">Planet E Communications, Inc.</label><label id=\"1560615\">Planet E Productions</label><label id=\"488315\">TWPENTY</label></sublabels></label>\n" +
                "    <label><id>2</id><name>Earthtones Recordings</name><contactinfo>Seasons Recordings&#13;\n" +
                "            2236 Pacific Avenue&#13;\n" +
                "            Suite D&#13;\n" +
                "            Costa Mesa, CA  92627&#13;\n" +
                "            &#13;\n" +
                "            tel: +1.949.574.5255&#13;\n" +
                "            fax: +1.949.574.0255&#13;\n" +
                "            &#13;\n" +
                "            email: jthinnes@seasonsrecordings.com&#13;\n" +
                "        </contactinfo><profile>California deep house label founded by [a=Jamie Thinnes]. Now defunct and continued as [l=Seasons Recordings].</profile><data_quality>Correct</data_quality><urls><url>http://www.seasonsrecordings.com/</url></urls></label>\n" +
                "    <label><images><image height=\"152\" type=\"primary\" uri=\"\" uri150=\"\" width=\"600\"/><image height=\"152\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/></images><id>3</id><name>Seasons Recordings</name><contactinfo>Seasons Recordings&#13;\n" +
                "            Costa Mesa, CA 92627&#13;\n" +
                "            &#13;\n" +
                "            Owner / Jamie Thinnes&#13;\n" +
                "            &#13;\n" +
                "            Tel 714-206-6146&#13;\n" +
                "            &#13;\n" +
                "            jthinnes@seasonsrecordings.com&#13;\n" +
                "            info@seasonsrecordings.com&#13;\n" +
                "        </contactinfo><profile>California deep-house label founded by [a=Jamie Thinnes]. &#13;\n" +
                "            The first ten records were released on [l=Earthtones Recordings].&#13;\n" +
                "        </profile><data_quality>Correct</data_quality><urls><url>http://www.seasonsrecordings.com</url></urls><sublabels><label id=\"297127\">Seasons Classics</label><label id=\"66542\">Seasons Limited</label></sublabels></label>\n" +
                "</labels>";
        ParserLabel parserLabel = new ParserLabel();
        List<DC_Label> lLabel = (List<DC_Label>)parserLabel.parse(sXML_label);
        assertTrue(lLabel.size() == 3);
        for (DC_Label label: lLabel) {
            assertTrue(label.id != null);
            assertTrue(label.idDC != null);
            assertTrue(!Core.isNull(label.sName));
        }
    }
}
