package org.dm;


import org.dm.model.DC_Artist;
import static org.junit.Assert.assertTrue;

import org.dm.model.DC_ArtistAlias;
import org.dm.model.DC_ArtistVariation;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class ParserArtistTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    //------------------------------------------------------------------
    @Test
    public void parseArtists() throws Exception {
        String sXML_Artist = "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> " +
                "<artists>                                                                                                                                     " +
                "	<artist>                                                                                                                                    " +
                "		<images>                                                                                                                                  " +
                "			<image height=\"450\" type=\"primary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                             " +
                "			<image height=\"771\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                           " +
                "		</images>                                                                                                                                 " +
                "		<id>1</id>                                                                                                                                " +
                "		<name>The Persuader</name>                                                                                                                " +
                "		<realname>Jesper Dahlbäck</realname>                                                                                                      " +
                "		<profile/>                                                                                                                                " +
                "		<data_quality>Needs Vote</data_quality>                                                                                                   " +
                "		<urls>                                                                                                                                    " +
                "			<url>https://en.wikipedia.org/wiki/Jesper_Dahlbäck</url>                                                                                " +
                "		</urls>                                                                                                                                   " +
                "		<namevariations>                                                                                                                          " +
                "			<name>Persuader</name>                                                                                                                  " +
                "			<name>The Presuader</name>                                                                                                              " +
                "		</namevariations>                                                                                                                         " +
                "		<aliases>                                                                                                                                 " +
                "			<name id=\"239\">Jesper Dahlbäck</name>                                                                                                 " +
                "			<name id=\"16055\">Groove Machine</name>                                                                                                " +
                "			<name id=\"19541\">Dick Track</name>                                                                                                    " +
                "			<name id=\"25227\">Lenk</name>                                                                                                          " +
                "			<name id=\"196957\">Janne Me' Amazonen</name>                                                                                           " +
                "			<name id=\"278760\">Faxid</name>                                                                                                        " +
                "			<name id=\"439150\">The Pinguin Man</name>                                                                                              " +
                "		</aliases>                                                                                                                                " +
                "	</artist>                                                                                                                                   " +
                "	<artist>                                                                                                                                    " +
                "		<id>2</id>                                                                                                                                " +
                "		<name>Mr. James Barth &amp; A.D.</name>                                                                                                   " +
                "		<realname>Cari Lekebusch &amp; Alexi Delano</realname>                                                                                    " +
                "		<profile/>                                                                                                                                " +
                "		<data_quality>Correct</data_quality>                                                                                                      " +
                "		<namevariations>                                                                                                                          " +
                "			<name>Mr Barth &amp; A.D.</name>                                                                                                        " +
                "			<name>MR JAMES BARTH &amp; A. D.</name>                                                                                                 " +
                "			<name>Mr. Barth &amp; A.D.</name>                                                                                                       " +
                "			<name>Mr. James Barth &amp; A. D.</name>                                                                                                " +
                "		</namevariations>                                                                                                                         " +
                "		<aliases>                                                                                                                                 " +
                "			<name id=\"2470\">Puente Latino</name>                                                                                                  " +
                "			<name id=\"19536\">Yakari &amp; Delano</name>                                                                                           " +
                "			<name id=\"103709\">Crushed Insect &amp; The Sick Puppy</name>                                                                          " +
                "			<name id=\"384581\">ADCL</name>                                                                                                         " +
                "			<name id=\"1779857\">Alexi Delano &amp; Cari Lekebusch</name>                                                                           " +
                "		</aliases>                                                                                                                                " +
                "		<members>                                                                                                                                 " +
                "			<id>26</id>                                                                                                                             " +
                "			<name id=\"26\">Alexi Delano</name>                                                                                                     " +
                "			<id>27</id>                                                                                                                             " +
                "			<name id=\"27\">Cari Lekebusch</name>                                                                                                   " +
                "		</members>                                                                                                                                " +
                "	</artist>                                                                                                                                   " +
                "	<artist>                                                                                                                                    " +
                "		<images>                                                                                                                                  " +
                "			<image height=\"500\" type=\"primary\" uri=\"\" uri150=\"\" width=\"383\"/>                                                             " +
                "			<image height=\"288\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"219\"/>                                                           " +
                "			<image height=\"630\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"500\"/>                                                           " +
                "			<image height=\"280\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"380\"/>                                                           " +
                "			<image height=\"370\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"399\"/>                                                           " +
                "			<image height=\"199\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"200\"/>                                                           " +
                "			<image height=\"436\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"550\"/>                                                           " +
                "			<image height=\"661\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                           " +
                "			<image height=\"270\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"461\"/>                                                           " +
                "		</images>                                                                                                                                 " +
                "		<id>3</id>                                                                                                                                " +
                "		<name>Josh Wink</name>                                                                                                                    " +
                "		<realname>Joshua Winkelman</realname>                                                                                                     " +
                "		<profile>After forming [l=Ovum Recordings]</profile>                                                                                      " +
                "		<data_quality>Needs Vote</data_quality>                                                                                                   " +
                "		<urls>                                                                                                                                    " +
                "			<url>http://www.joshwink.com/</url>                                                                                                     " +
                "			<url>http://www.ovumrecordings.com/artists/josh-wink/</url>                                                                             " +
                "			<url>http://www.facebook.com/JoshWinkOfficial</url>                                                                                     " +
                "			<url>http://twitter.com/joshwink1</url>                                                                                                 " +
                "			<url>http://instagram.com/joshwink1</url>                                                                                               " +
                "			<url>https://www.bookogs.com/credit/208362-josh-wink</url>                                                                              " +
                "			<url>http://myspace.com/joshwink</url>                                                                                                  " +
                "			<url>http://myspace.com/ovumrecordings</url>                                                                                            " +
                "			<url>http://www.discogs.com/user/JoshWink</url>                                                                                         " +
                "			<url>http://www.discogs.com/user/josh_wink</url>                                                                                        " +
                "			<url>http://www.songkick.com/artists/250682-josh-wink</url>                                                                             " +
                "			<url>http://en.wikipedia.org/wiki/Josh_Wink</url>                                                                                       " +
                "			<url>http://www.whosampled.com/Josh-Wink/</url>                                                                                         " +
                "			<url>https://soundcloud.com/joshwinkofficial</url>                                                                                      " +
                "			<url>http://www.youtube.com/user/JoshWinkVEVO</url>                                                                                     " +
                "			<url>http://www.dailymotion.com/JoshWink-vevo</url>                                                                                     " +
                "			<url>https://joshwink.bandcamp.com</url>                                                                                                " +
                "		</urls>                                                                                                                                   " +
                "		<namevariations>                                                                                                                          " +
                "			<name>DJ Josh Wink</name>                                                                                                               " +
                "			<name>DJ Wink</name>                                                                                                                    " +
                "			<name>Dosh Wink</name>                                                                                                                  " +
                "			<name>J Wink</name>                                                                                                                     " +
                "			<name>J. Wink</name>                                                                                                                    " +
                "			<name>J. Wink (DJ  Wink)</name>                                                                                                         " +
                "			<name>J. Winkelman</name>                                                                                                               " +
                "			<name>J. Winkelmann</name>                                                                                                              " +
                "			<name>J. Wnk</name>                                                                                                                     " +
                "			<name>J.Wink</name>                                                                                                                     " +
                "			<name>J.Winkelman</name>                                                                                                                " +
                "			<name>Josh Wink \"DJ Wink\"</name>                                                                                                      " +
                "			<name>Josh Wink (DJ Wink)</name>                                                                                                        " +
                "			<name>Josh Wink (Dj Winx)</name>                                                                                                        " +
                "			<name>Josh Winkelman</name>                                                                                                             " +
                "			<name>Josh Winkelmann</name>                                                                                                            " +
                "			<name>Josh Winks</name>                                                                                                                 " +
                "			<name>Josh Winx</name>                                                                                                                  " +
                "			<name>JW</name>                                                                                                                         " +
                "			<name>Linx</name>                                                                                                                       " +
                "			<name>Winc</name>                                                                                                                       " +
                "			<name>Wing</name>                                                                                                                       " +
                "			<name>Wings</name>                                                                                                                      " +
                "			<name>Wink</name>                                                                                                                       " +
                "			<name>Winks</name>                                                                                                                      " +
                "			<name>Winx</name>                                                                                                                       " +
                "			<name>Winxs</name>                                                                                                                      " +
                "		</namevariations>                                                                                                                         " +
                "		<aliases>                                                                                                                                 " +
                "			<name id=\"11217\">Size 9</name>                                                                                                        " +
                "			<name id=\"95949\">The Crusher</name>                                                                                                   " +
                "			<name id=\"284057\">Dinky Dog</name>                                                                                                    " +
                "			<name id=\"370936\">Accent (3)</name>                                                                                                   " +
                "			<name id=\"870371\">J. Dawg</name>                                                                                                      " +
                "		</aliases>                                                                                                                                " +
                "		<groups>                                                                                                                                  " +
                "			<name id=\"34803\">E-Culture</name>                                                                                                     " +
                "			<name id=\"55692\">Abundance Of Cups</name>                                                                                             " +
                "			<name id=\"579249\">Jack Jones (4)</name>                                                                                               " +
                "			<name id=\"844878\">Just King And Wink</name>                                                                                           " +
                "			<name id=\"1642275\">The Force (23)</name>                                                                                              " +
                "		</groups>                                                                                                                                 " +
                "	</artist>                                                                                                                                   " +
                "	<artist>                                                                                                                                    " +
                "		<images>                                                                                                                                  " +
                "			<image height=\"900\" type=\"primary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                             " +
                "			<image height=\"400\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                           " +
                "			<image height=\"400\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                           " +
                "			<image height=\"210\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"311\"/>                                                           " +
                "			<image height=\"259\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"400\"/>                                                           " +
                "			<image height=\"600\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"487\"/>                                                           " +
                "			<image height=\"464\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"348\"/>                                                           " +
                "		</images>                                                                                                                                 " +
                "		<id>4</id>                                                                                                                                " +
                "		<name>Johannes Heil</name>                                                                                                                " +
                "		<realname>Johannes Heil</realname>                                                                                                        " +
                "		<profile>Electronic </profile>                                                                                                            " +
                "		<data_quality>Needs Vote</data_quality>                                                                                                   " +
                "		<urls>                                                                                                                                    " +
                "			<url>http://johannes-heil.com/</url>                                                                                                    " +
                "			<url>https://www.facebook.com/johannesheilofficial</url>                                                                                " +
                "			<url>https://twitter.com/Johannes_Heil</url>                                                                                            " +
                "		</urls>                                                                                                                                   " +
                "		<namevariations>                                                                                                                          " +
                "			<name>Heil</name>                                                                                                                       " +
                "			<name>Heil, Johannes</name>                                                                                                             " +
                "			<name>Hell</name>                                                                                                                       " +
                "			<name>J Heil</name>                                                                                                                     " +
                "			<name>J. Heil</name>                                                                                                                    " +
                "			<name>J. Heill</name>                                                                                                                   " +
                "			<name>J.Heil</name>                                                                                                                     " +
                "			<name>JH</name>                                                                                                                         " +
                "			<name>Joh. Heil</name>                                                                                                                  " +
                "			<name>Johannas Heil</name>                                                                                                              " +
                "			<name>Johannes Hell</name>                                                                                                              " +
                "		</namevariations>                                                                                                                         " +
                "		<aliases>                                                                                                                                 " +
                "			<name id=\"12490\">The Trinity</name>                                                                                                   " +
                "			<name id=\"23236\">Unity Gain</name>                                                                                                    " +
                "			<name id=\"70235\">Jim Henson Project</name>                                                                                            " +
                "			<name id=\"78741\">Antonio Montana El Rey</name>                                                                                        " +
                "			<name id=\"120636\">Age Beats</name>                                                                                                    " +
                "			<name id=\"200347\">The X Act</name>                                                                                                    " +
                "			<name id=\"321433\">Think Tank (2)</name>                                                                                               " +
                "			<name id=\"811025\">The Hidden (2)</name>                                                                                               " +
                "			<name id=\"3558838\">Cryptik</name>                                                                                                     " +
                "		</aliases>                                                                                                                                " +
                "		<groups>                                                                                                                                  " +
                "			<name id=\"10281\">Item One</name>                                                                                                      " +
                "			<name id=\"30969\">C.R.S.</name>                                                                                                        " +
                "			<name id=\"35683\">Project 69</name>                                                                                                    " +
                "			<name id=\"60367\">State Of Chaos</name>                                                                                                " +
                "			<name id=\"239403\">Question Authority (2)</name>                                                                                       " +
                "		</groups>                                                                                                                                 " +
                "	</artist>                                                                                                                                   " +
                "</artists>                                                                                                                                    " +
                "";
        ParserArtist parserArtist = new ParserArtist();
        List<DC_Artist> lArtist = (List<DC_Artist>)parserArtist.parse(sXML_Artist);

        assertTrue(lArtist.size() == 4);
        assertTrue(lArtist.get(0).lAlias.size() == 7 && lArtist.get(0).lVariation.size() == 2);
        assertTrue(lArtist.get(1).lAlias.size() == 5 && lArtist.get(1).lVariation.size() == 4);
        assertTrue(lArtist.get(2).lAlias.size() == 5 && lArtist.get(2).lVariation.size() == 27);
        assertTrue(lArtist.get(3).lAlias.size() == 9 && lArtist.get(3).lVariation.size() == 11);

        assertTrue(!lArtist.get(0).sName.isEmpty());
        assertTrue(!lArtist.get(1).sName.isEmpty());
        assertTrue(!lArtist.get(2).sName.isEmpty());
        assertTrue(!lArtist.get(3).sName.isEmpty());

        assertTrue(!lArtist.get(0).sNameShort.isEmpty());
        assertTrue(!lArtist.get(1).sNameShort.isEmpty());
        assertTrue(!lArtist.get(2).sNameShort.isEmpty());
        assertTrue(!lArtist.get(3).sNameShort.isEmpty());

        for (DC_Artist artist: lArtist) {
            for (DC_ArtistAlias artistAlias: artist.lAlias) {
                assertTrue(artistAlias.idArtist.equals(artist.id));
                assertTrue(artistAlias.idArtistDC.equals(artist.idDC));
            }
            for (DC_ArtistVariation artistVariation: artist.lVariation) {
                assertTrue(artistVariation.idArtist.equals(artist.id));
                assertTrue(artistVariation.idArtistDC.equals(artist.idDC));
            }
        }
    }
}
