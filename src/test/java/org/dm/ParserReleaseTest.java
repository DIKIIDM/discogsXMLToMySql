package org.dm;

import org.dm.model.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ParserReleaseTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    //------------------------------------------------------------------
    @Test
    public void parseReleases() throws Exception {
        String sXML_release_01 = "" +
                "	<release id=\"2\" status=\"Accepted\">                                                                                                                  " +
                "		<images>                                                                                                                                              " +
                "			<image height=\"394\" type=\"primary\" uri=\"\" uri150=\"\" width=\"400\"/>                                                                         " +
                "			<image height=\"600\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                                       " +
                "			<image height=\"600\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                                                       " +
                "		</images>                                                                                                                                             " +
                "		<artists>                                                                                                                                             " +
                "			<artist>                                                                                                                                            " +
                "				<id>2</id>                                                                                                                                        " +
                "				<name>Mr. James Barth &amp; A.D.</name>                                                                                                           " +
                "				<anv/>                                                                                                                                            " +
                "				<join/>                                                                                                                                           " +
                "				<role/>                                                                                                                                           " +
                "				<tracks/>                                                                                                                                         " +
                "			</artist>                                                                                                                                           " +
                "		</artists>                                                                                                                                            " +
                "		<title>Knockin' Boots Vol 2 Of 2</title>                                                                                                              " +
                "		<labels>                                                                                                                                              " +
                "			<label catno=\"SK 026\" id=\"5\" name=\"Svek\"/>                                                                                                    " +
                "			<label catno=\"SK026\" id=\"5\" name=\"Svek\"/>                                                                                                     " +
                "		</labels>                                                                                                                                             " +
                "		<extraartists>                                                                                                                                        " +
                "			<artist>                                                                                                                                            " +
                "				<id>26</id>                                                                                                                                       " +
                "				<name>Alexi Delano</name>                                                                                                                         " +
                "				<anv/>                                                                                                                                            " +
                "				<join/>                                                                                                                                           " +
                "				<role>Producer, Recorded By</role>                                                                                                                " +
                "				<tracks/>                                                                                                                                         " +
                "			</artist>                                                                                                                                           " +
                "			<artist>                                                                                                                                            " +
                "				<id>27</id>                                                                                                                                       " +
                "				<name>Cari Lekebusch</name>                                                                                                                       " +
                "				<anv/>                                                                                                                                            " +
                "				<join/>                                                                                                                                           " +
                "				<role>Producer, Recorded By</role>                                                                                                                " +
                "				<tracks/>                                                                                                                                         " +
                "			</artist>                                                                                                                                           " +
                "			<artist>                                                                                                                                            " +
                "				<id>26</id>                                                                                                                                       " +
                "				<name>Alexi Delano</name>                                                                                                                         " +
                "				<anv>A. Delano</anv>                                                                                                                              " +
                "				<join/>                                                                                                                                           " +
                "				<role>Written-By</role>                                                                                                                           " +
                "				<tracks/>                                                                                                                                         " +
                "			</artist>                                                                                                                                           " +
                "			<artist>                                                                                                                                            " +
                "				<id>27</id>                                                                                                                                       " +
                "				<name>Cari Lekebusch</name>                                                                                                                       " +
                "				<anv>C. Lekebusch</anv>                                                                                                                           " +
                "				<join/>                                                                                                                                           " +
                "				<role>Written-By</role>                                                                                                                           " +
                "				<tracks/>                                                                                                                                         " +
                "			</artist>                                                                                                                                           " +
                "		</extraartists>                                                                                                                                       " +
                "		<formats>                                                                                                                                             " +
                "			<format name=\"Vinyl\" qty=\"1\" text=\"\">                                                                                                         " +
                "				<descriptions>                                                                                                                                    " +
                "					<description>12\"</description>                                                                                                                 " +
                "					<description>33 ? RPM</description>                                                                                                             " +
                "				</descriptions>                                                                                                                                   " +
                "			</format>                                                                                                                                           " +
                "		</formats>                                                                                                                                            " +
                "		<genres>                                                                                                                                              " +
                "			<genre>Electronic</genre>                                                                                                                           " +
                "		</genres>                                                                                                                                             " +
                "		<styles>                                                                                                                                              " +
                "			<style>Broken Beat</style>                                                                                                                          " +
                "			<style>Techno</style>                                                                                                                               " +
                "			<style>Tech House</style>                                                                                                                           " +
                "		</styles>                                                                                                                                             " +
                "		<country>Sweden</country>                                                                                                                             " +
                "		<released>1998-06-00</released>                                                                                                                       " +
                "		<notes>All joints recorded in NYC (Dec.97).</notes>                                                                                                   " +
                "		<data_quality>Correct</data_quality>                                                                                                                  " +
                "		<master_id is_main_release=\"true\">713738</master_id>                                                                                                " +
                "		<tracklist>                                                                                                                                           " +
                "			<track>                                                                                                                                             " +
                "				<position>A1</position>                                                                                                                           " +
                "				<title>A Sea Apart</title>                                                                                                                        " +
                "				<duration>5:08</duration>                                                                                                                         " +
                "			</track>                                                                                                                                            " +
                "			<track>                                                                                                                                             " +
                "				<position>A2</position>                                                                                                                           " +
                "				<title>Dutchmaster</title>                                                                                                                        " +
                "				<duration>4:21</duration>                                                                                                                         " +
                "			</track>                                                                                                                                            " +
                "			<track>                                                                                                                                             " +
                "				<position>B1</position>                                                                                                                           " +
                "				<title>Inner City Lullaby</title>                                                                                                                 " +
                "				<duration>4:22</duration>                                                                                                                         " +
                "			</track>                                                                                                                                            " +
                "			<track>                                                                                                                                             " +
                "				<position>B2</position>                                                                                                                           " +
                "				<title>Yeah Kid!</title>                                                                                                                          " +
                "				<duration>4:46</duration>                                                                                                                         " +
                "			</track>                                                                                                                                            " +
                "		</tracklist>                                                                                                                                          " +
                "		<identifiers>                                                                                                                                         " +
                "			<identifier description=\"Side A Runout Etching\" type=\"Matrix / Runout\" value=\"MPO SK026-A -J.T.S.-\"/>                                         " +
                "			<identifier description=\"Side B Runout Etching\" type=\"Matrix / Runout\" value=\"MPO SK026-B -J.T.S.-\"/>                                         " +
                "		</identifiers>                                                                                                                                        " +
                "		<videos>                                                                                                                                              " +
                "			<video duration=\"310\" embed=\"true\" src=\"https://www.youtube.com/watch?v=MIgQNVhYILA\">                                                         " +
                "				<title>Mr. James Barth &amp; A.D. - A Sea Apart</title>                                                                                           " +
                "				<description>Mr. James Barth &amp; A.D. - A Sea Apart</description>                                                                               " +
                "			</video>                                                                                                                                            " +
                "			<video duration=\"265\" embed=\"true\" src=\"https://www.youtube.com/watch?v=LgLchSRehhc\">                                                         " +
                "				<title>Mr. James Barth &amp; A.D. - Dutchmaster</title>                                                                                           " +
                "				<description>Mr. James Barth &amp; A.D. - Dutchmaster</description>                                                                               " +
                "			</video>                                                                                                                                            " +
                "			<video duration=\"260\" embed=\"true\" src=\"https://www.youtube.com/watch?v=iaqHaULlqqg\">                                                         " +
                "				<title>Mr. James Barth &amp; A.D. - Inner City Lullaby</title>                                                                                    " +
                "				<description>Mr. James Barth &amp; A.D. - Inner City Lullaby</description>                                                                        " +
                "			</video>                                                                                                                                            " +
                "			<video duration=\"290\" embed=\"true\" src=\"https://www.youtube.com/watch?v=x_Os7b-iWKs\">                                                         " +
                "				<title>Mr. James Barth &amp; A.D. - Yeah Kid!</title>                                                                                             " +
                "				<description>Mr. James Barth &amp; A.D. - Yeah Kid!</description>                                                                                 " +
                "			</video>                                                                                                                                            " +
                "		</videos>                                                                                                                                             " +
                "		<companies>                                                                                                                                           " +
                "			<company>                                                                                                                                           " +
                "				<id>266169</id>                                                                                                                                   " +
                "				<name>JTS Studios</name>                                                                                                                          " +
                "				<catno/>                                                                                                                                          " +
                "				<entity_type>29</entity_type>                                                                                                                     " +
                "				<entity_type_name>Mastered At</entity_type_name>                                                                                                  " +
                "				<resource_url>https://api.discogs.com/labels/266169</resource_url>                                                                                " +
                "			</company>                                                                                                                                          " +
                "			<company>                                                                                                                                           " +
                "				<id>56025</id>                                                                                                                                    " +
                "				<name>MPO</name>                                                                                                                                  " +
                "				<catno/>                                                                                                                                          " +
                "				<entity_type>17</entity_type>                                                                                                                     " +
                "				<entity_type_name>Pressed By</entity_type_name>                                                                                                   " +
                "				<resource_url>https://api.discogs.com/labels/56025</resource_url>                                                                                 " +
                "			</company>                                                                                                                                          " +
                "		</companies>                                                                                                                                          " +
                "	</release>                                                                                                                                              ";
        String sXML_release_02 = "" +
                "<release id=\"103\" status=\"Accepted\">                                                                                                                             " +
                "	<images>                                                                                                                                                           " +
                "		<image height=\"200\" type=\"primary\" uri=\"\" uri150=\"\" width=\"200\"/>                                                                                      " +
                "		<image height=\"225\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"300\"/>                                                                                    " +
                "	</images>                                                                                                                                                          " +
                "	<artists>                                                                                                                                                          " +
                "		<artist>                                                                                                                                                         " +
                "			<id>194</id>                                                                                                                                                   " +
                "			<name>Various</name>                                                                                                                                           " +
                "			<anv/>                                                                                                                                                         " +
                "			<join/>                                                                                                                                                        " +
                "			<role/>                                                                                                                                                        " +
                "			<tracks/>                                                                                                                                                      " +
                "		</artist>                                                                                                                                                        " +
                "	</artists>                                                                                                                                                         " +
                "	<title>The Necessary EP</title>                                                                                                                                    " +
                "	<labels>                                                                                                                                                           " +
                "		<label catno=\"NT006\" id=\"44\" name=\"Nordic Trax\"/>                                                                                                          " +
                "	</labels>                                                                                                                                                          " +
                "	<extraartists/>                                                                                                                                                    " +
                "	<formats>                                                                                                                                                          " +
                "		<format name=\"Vinyl\" qty=\"1\" text=\"\">                                                                                                                      " +
                "			<descriptions>                                                                                                                                                 " +
                "				<description>12\"</description>                                                                                                                              " +
                "				<description>EP</description>                                                                                                                                " +
                "			</descriptions>                                                                                                                                                " +
                "		</format>                                                                                                                                                        " +
                "	</formats>                                                                                                                                                         " +
                "	<genres>                                                                                                                                                           " +
                "		<genre>Electronic</genre>                                                                                                                                        " +
                "	</genres>                                                                                                                                                          " +
                "	<styles>                                                                                                                                                           " +
                "		<style>Deep House</style>                                                                                                                                        " +
                "	</styles>                                                                                                                                                          " +
                "	<country>Canada</country>                                                                                                                                          " +
                "	<released>1999-00-00</released>                                                                                                                                    " +
                "	<data_quality>Correct</data_quality>                                                                                                                               " +
                "	<tracklist>                                                                                                                                                        " +
                "		<track>                                                                                                                                                          " +
                "			<position>A1</position>                                                                                                                                        " +
                "			<title>K2morrow</title>                                                                                                                                        " +
                "			<duration/>                                                                                                                                                    " +
                "			<artists>                                                                                                                                                      " +
                "				<artist>                                                                                                                                                     " +
                "					<id>195</id>                                                                                                                                               " +
                "					<name>Peter Hecher</name>                                                                                                                                  " +
                "					<anv/>                                                                                                                                                     " +
                "					<join/>                                                                                                                                                    " +
                "					<role/>                                                                                                                                                    " +
                "					<tracks/>                                                                                                                                                  " +
                "				</artist>                                                                                                                                                    " +
                "			</artists>                                                                                                                                                     " +
                "		</track>                                                                                                                                                         " +
                "		<track>                                                                                                                                                          " +
                "			<position>A2</position>                                                                                                                                        " +
                "			<title>The Disco Man</title>                                                                                                                                   " +
                "			<duration/>                                                                                                                                                    " +
                "			<artists>                                                                                                                                                      " +
                "				<artist>                                                                                                                                                     " +
                "					<id>195</id>                                                                                                                                               " +
                "					<name>Peter Hecher</name>                                                                                                                                  " +
                "					<anv/>                                                                                                                                                     " +
                "					<join/>                                                                                                                                                    " +
                "					<role/>                                                                                                                                                    " +
                "					<tracks/>                                                                                                                                                  " +
                "				</artist>                                                                                                                                                    " +
                "			</artists>                                                                                                                                                     " +
                "		</track>                                                                                                                                                         " +
                "		<track>                                                                                                                                                          " +
                "			<position>B1</position>                                                                                                                                        " +
                "			<title>Making Changes (4am Vibez)</title>                                                                                                                      " +
                "			<duration/>                                                                                                                                                    " +
                "			<artists>                                                                                                                                                      " +
                "				<artist>                                                                                                                                                     " +
                "					<id>196</id>                                                                                                                                               " +
                "					<name>Aaronz</name>                                                                                                                                        " +
                "					<anv/>                                                                                                                                                     " +
                "					<join/>                                                                                                                                                    " +
                "					<role/>                                                                                                                                                    " +
                "					<tracks/>                                                                                                                                                  " +
                "				</artist>                                                                                                                                                    " +
                "			</artists>                                                                                                                                                     " +
                "		</track>                                                                                                                                                         " +
                "		<track>                                                                                                                                                          " +
                "			<position>B2</position>                                                                                                                                        " +
                "			<title>Up Jumped The Boogie</title>                                                                                                                            " +
                "			<duration/>                                                                                                                                                    " +
                "			<artists>                                                                                                                                                      " +
                "				<artist>                                                                                                                                                     " +
                "					<id>197</id>                                                                                                                                               " +
                "					<name>Sea To Sky</name>                                                                                                                                    " +
                "					<anv/>                                                                                                                                                     " +
                "					<join/>                                                                                                                                                    " +
                "					<role/>                                                                                                                                                    " +
                "					<tracks/>                                                                                                                                                  " +
                "				</artist>                                                                                                                                                    " +
                "			</artists>                                                                                                                                                     " +
                "		</track>                                                                                                                                                         " +
                "	</tracklist>                                                                                                                                                       " +
                "	<identifiers/>                                                                                                                                                     " +
                "	<videos>                                                                                                                                                           " +
                "		<video duration=\"395\" embed=\"true\" src=\"https://www.youtube.com/watch?v=CmFYUEcD0Xs\">                                                                      " +
                "			<title>K2morrow [Original Mix] - Peter Hecher</title>                                                                                                          " +
                "			<description>K2morrow [Original Mix] - Peter Hecher</description>                                                                                              " +
                "		</video>                                                                                                                                                         " +
                "	</videos>                                                                                                                                                          " +
                "	<companies/>                                                                                                                                                       " +
                "</release>                                                                                                                                                           " +
                "";
        String sXML_release_03 = "" +
                "<release id=\"102\" status=\"Accepted\">                                                                                  " +
                "	<images>                                                                                                                " +
                "		<image height=\"593\" type=\"primary\" uri=\"\" uri150=\"\" width=\"600\"/>                                           " +
                "		<image height=\"593\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                         " +
                "		<image height=\"419\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"419\"/>                                         " +
                "		<image height=\"601\" type=\"secondary\" uri=\"\" uri150=\"\" width=\"600\"/>                                         " +
                "	</images>                                                                                                               " +
                "	<artists>                                                                                                               " +
                "		<artist>                                                                                                              " +
                "			<id>193</id>                                                                                                        " +
                "			<name>DKMA</name>                                                                                                   " +
                "			<anv/>                                                                                                              " +
                "			<join/>                                                                                                             " +
                "			<role/>                                                                                                             " +
                "			<tracks/>                                                                                                           " +
                "		</artist>                                                                                                             " +
                "	</artists>                                                                                                              " +
                "	<title>Why U Wanna?</title>                                                                                             " +
                "	<labels>                                                                                                                " +
                "		<label catno=\"RITUAL 1\" id=\"64\" name=\"Ritual Recordings\"/>                                                      " +
                "	</labels>                                                                                                               " +
                "	<extraartists>                                                                                                          " +
                "		<artist>                                                                                                              " +
                "			<id>147370</id>                                                                                                     " +
                "			<name>Mike Walsh</name>                                                                                             " +
                "			<anv>M. Walsh</anv>                                                                                                 " +
                "			<join/>                                                                                                             " +
                "			<role>Design</role>                                                                                                 " +
                "			<tracks/>                                                                                                           " +
                "		</artist>                                                                                                             " +
                "		<artist>                                                                                                              " +
                "			<id>73316</id>                                                                                                      " +
                "			<name>Dana Kelley</name>                                                                                            " +
                "			<anv/>                                                                                                              " +
                "			<join/>                                                                                                             " +
                "			<role>Written-By, Producer</role>                                                                                   " +
                "			<tracks/>                                                                                                           " +
                "		</artist>                                                                                                             " +
                "	</extraartists>                                                                                                         " +
                "	<formats>                                                                                                               " +
                "		<format name=\"Vinyl\" qty=\"1\" text=\"\">                                                                           " +
                "			<descriptions>                                                                                                      " +
                "				<description>12\"</description>                                                                                   " +
                "				<description>33 ? RPM</description>                                                                               " +
                "			</descriptions>                                                                                                     " +
                "		</format>                                                                                                             " +
                "	</formats>                                                                                                              " +
                "	<genres>                                                                                                                " +
                "		<genre>Electronic</genre>                                                                                             " +
                "	</genres>                                                                                                               " +
                "	<styles>                                                                                                                " +
                "		<style>Deep House</style>                                                                                             " +
                "		<style>Tech House</style>                                                                                             " +
                "	</styles>                                                                                                               " +
                "	<country>US</country>                                                                                                   " +
                "	<released>1999</released>                                                                                               " +
                "	<notes>A2 remixed at Minimum Studios for Cornershots Ltd.&#13;Designed at The Media Farm.</notes>                       " +
                "	<data_quality>Complete and Correct</data_quality>                                                                       " +
                "	<tracklist>                                                                                                             " +
                "		<track>                                                                                                               " +
                "			<position>A1</position>                                                                                             " +
                "			<title>Why U Wanna?</title>                                                                                         " +
                "			<duration/>                                                                                                         " +
                "			<extraartists>                                                                                                      " +
                "				<artist>                                                                                                          " +
                "					<id>1147165</id>                                                                                                " +
                "					<name>Randy Johnson (3)</name>                                                                                  " +
                "					<anv/>                                                                                                          " +
                "					<join/>                                                                                                         " +
                "					<role>Vocals</role>                                                                                             " +
                "					<tracks/>                                                                                                       " +
                "				</artist>                                                                                                         " +
                "			</extraartists>                                                                                                     " +
                "		</track>                                                                                                              " +
                "		<track>                                                                                                               " +
                "			<position>A2</position>                                                                                             " +
                "			<title>Why U Wanna? (Alexi's Cornershot Remix)</title>                                                              " +
                "			<duration/>                                                                                                         " +
                "			<extraartists>                                                                                                      " +
                "				<artist>                                                                                                          " +
                "					<id>26</id>                                                                                                     " +
                "					<name>Alexi Delano</name>                                                                                       " +
                "					<anv/>                                                                                                          " +
                "					<join/>                                                                                                         " +
                "					<role>Remix</role>                                                                                              " +
                "					<tracks/>                                                                                                       " +
                "				</artist>                                                                                                         " +
                "				<artist>                                                                                                          " +
                "					<id>1147165</id>                                                                                                " +
                "					<name>Randy Johnson (3)</name>                                                                                  " +
                "					<anv/>                                                                                                          " +
                "					<join/>                                                                                                         " +
                "					<role>Vocals</role>                                                                                             " +
                "					<tracks/>                                                                                                       " +
                "				</artist>                                                                                                         " +
                "			</extraartists>                                                                                                     " +
                "		</track>                                                                                                              " +
                "		<track>                                                                                                               " +
                "			<position>B1</position>                                                                                             " +
                "			<title>Inside My Soul</title>                                                                                       " +
                "			<duration/>                                                                                                         " +
                "		</track>                                                                                                              " +
                "		<track>                                                                                                               " +
                "			<position>B2</position>                                                                                             " +
                "			<title>Attack (Don't Let No One)</title>                                                                            " +
                "			<duration/>                                                                                                         " +
                "		</track>                                                                                                              " +
                "	</tracklist>                                                                                                            " +
                "	<identifiers/>                                                                                                          " +
                "	<videos>                                                                                                                " +
                "		<video duration=\"341\" embed=\"true\" src=\"https://www.youtube.com/watch?v=ocXmY7Ddt54\">                           " +
                "			<title>Why U Wanna? (Alexi's Cornershot Remix) / DKMA / Why U Wanna? [1999]</title>                                 " +
                "			<description>Why U Wanna? (Alexi's Cornershot Remix) / DKMA / Why U Wanna? [1999]</description>                     " +
                "		</video>                                                                                                              " +
                "		<video duration=\"345\" embed=\"true\" src=\"https://www.youtube.com/watch?v=nBCNt4SPJEQ\">                           " +
                "			<title>DKMA: Attack (Don't Let No One)</title>                                                                      " +
                "			<description>DKMA: Attack (Don't Let No One)</description>                                                          " +
                "		</video>                                                                                                              " +
                "		<video duration=\"477\" embed=\"true\" src=\"https://www.youtube.com/watch?v=gLwvkB2p4-Q\">                           " +
                "			<title>DKMA ?– Inside My Soul</title>                                                                               " +
                "			<description>DKMA ?– Inside My Soul</description>                                                                   " +
                "		</video>                                                                                                              " +
                "		<video duration=\"406\" embed=\"true\" src=\"https://www.youtube.com/watch?v=vNZUThO85y4\">                           " +
                "			<title>Dkma - Why U Wanna</title>                                                                                   " +
                "			<description>Dkma - Why U Wanna</description>                                                                       " +
                "		</video>                                                                                                              " +
                "	</videos>                                                                                                               " +
                "	<companies/>                                                                                                            " +
                "</release>                                                                                                                " +
                "";
        ParserRelease parserRelease = new ParserRelease();
        List<DC_Release> lReleases = (List<DC_Release>)parserRelease.parser(sXML_release_01);
        assertTrue(lReleases.get(0).lArtist.size() == 1);
        assertTrue(lReleases.get(0).lExtraArtist.size() == 4);
        assertTrue(lReleases.get(0).lStyle.size() == 3);
        assertTrue(lReleases.get(0).lGenre.size() == 1);
        assertTrue(lReleases.get(0).lTrack.size() == 4);
        for (DC_Release release: lReleases) {
            for (DC_ReleaseArtist artist: release.lArtist) {
                assertTrue(artist.idRelease.equals(release.id));
                assertTrue(artist.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(artist.sName));
            }
            for (DC_ReleaseArtist artist: release.lExtraArtist) {
                assertTrue(artist.idRelease.equals(release.id));
                assertTrue(artist.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(artist.sName));
            }
            for (DC_ReleaseGenre genre: release.lGenre) {
                assertTrue(genre.idRelease.equals(release.id));
                assertTrue(genre.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(genre.sName));
            }
            for (DC_ReleaseStyle style: release.lStyle) {
                assertTrue(style.idRelease.equals(release.id));
                assertTrue(style.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(style.sName));
            }
            for (DC_ReleaseTrack track: release.lTrack) {
                assertTrue(track.idRelease.equals(release.id));
                assertTrue(track.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(track.sTitle));
            }
        }

        lReleases = (List<DC_Release>)parserRelease.parser(sXML_release_02);
        assertTrue(lReleases.get(0).lArtist.size() == 5);
        assertTrue(lReleases.get(0).lExtraArtist.size() == 0);
        assertTrue(lReleases.get(0).lStyle.size() == 1);
        assertTrue(lReleases.get(0).lGenre.size() == 1);
        assertTrue(lReleases.get(0).lTrack.size() == 4);
        for (DC_Release release: lReleases) {
            for (DC_ReleaseArtist artist: release.lArtist) {
                assertTrue(artist.idRelease.equals(release.id));
                assertTrue(artist.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(artist.sName));
            }
            for (DC_ReleaseArtist artist: release.lExtraArtist) {
                assertTrue(artist.idRelease.equals(release.id));
                assertTrue(artist.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(artist.sName));
            }
            for (DC_ReleaseGenre genre: release.lGenre) {
                assertTrue(genre.idRelease.equals(release.id));
                assertTrue(genre.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(genre.sName));
            }
            for (DC_ReleaseStyle style: release.lStyle) {
                assertTrue(style.idRelease.equals(release.id));
                assertTrue(style.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(style.sName));
            }
            for (DC_ReleaseTrack track: release.lTrack) {
                assertTrue(track.idRelease.equals(release.id));
                assertTrue(track.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(track.sTitle));
            }
        }

        lReleases = (List<DC_Release>)parserRelease.parser(sXML_release_03);
        assertTrue(lReleases.get(0).lArtist.size() == 1);
        assertTrue(lReleases.get(0).lExtraArtist.size() == 5);
        assertTrue(lReleases.get(0).lStyle.size() == 2);
        assertTrue(lReleases.get(0).lGenre.size() == 1);
        assertTrue(lReleases.get(0).lTrack.size() == 4);
        for (DC_Release release: lReleases) {
            for (DC_ReleaseArtist artist: release.lArtist) {
                assertTrue(artist.idRelease.equals(release.id));
                assertTrue(artist.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(artist.sName));
            }
            for (DC_ReleaseArtist artist: release.lExtraArtist) {
                assertTrue(artist.idRelease.equals(release.id));
                assertTrue(artist.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(artist.sName));
            }
            for (DC_ReleaseGenre genre: release.lGenre) {
                assertTrue(genre.idRelease.equals(release.id));
                assertTrue(genre.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(genre.sName));
            }
            for (DC_ReleaseStyle style: release.lStyle) {
                assertTrue(style.idRelease.equals(release.id));
                assertTrue(style.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(style.sName));
            }
            for (DC_ReleaseTrack track: release.lTrack) {
                assertTrue(track.idRelease.equals(release.id));
                assertTrue(track.idReleaseDC.equals(release.idDC));
                assertTrue(!Core.isNull(track.sTitle));
            }
        }
    }
}
