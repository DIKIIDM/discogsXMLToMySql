package org.dm;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    //------------------------------------------------------------------
    public static void main(String[] args) {
        try {
            xmlToMysql(args);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
    }
    //------------------------------------------------------------------
    public static void xmlToMysql(String[] args) {
        Options options = new Options();
        try {
            Option oPathToXMLFolder= new Option("f", "pathToXMLFolder", true, "path to the folder, containing XML files");
            oPathToXMLFolder.setRequired(true);
            options.addOption(oPathToXMLFolder);

            Option oDatabaseUsername = new Option("u", "databaseUsername", true, "database username");
            oDatabaseUsername.setRequired(true);
            options.addOption(oDatabaseUsername);

            Option oDatabaseUserPassword = new Option("p", "databaseUserPassword", true, "database user password");
            oDatabaseUserPassword.setRequired(true);
            options.addOption(oDatabaseUserPassword);

            Option oDatabaseURL = new Option("url", "databaseURL", true, "database url, example: jdbc:mysql://localhost/databaseName");
            oDatabaseURL.setRequired(true);
            options.addOption(oDatabaseURL);

            CommandLine cmd = new DefaultParser().parse(options, args);
            xmlToMysql(cmd.getOptionValue("pathToXMLFolder")
                      ,cmd.getOptionValue("databaseUsername")
                      ,cmd.getOptionValue("databaseUserPassword")
                      ,cmd.getOptionValue("databaseURL")
            );
        } catch (ParseException e) {
            logger.error(e.getMessage());
            new HelpFormatter().printHelp("discogsXMlToMySql", options);
            throw new RuntimeException("Wrong arguments");
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    //------------------------------------------------------------------
    private static void xmlToMysql(String pathToXMLFolder
                                  ,String databaseUsername
                                  ,String databaseUserPassword
                                  ,String databaseURL)
    {
        Map<Integer, Integer> idsLabel = new HashMap<>();
        Map<Integer, Integer> idsArtist = new HashMap<>();

        File xmlFolder = new File(pathToXMLFolder);
        if (!xmlFolder.isDirectory())
            throw new RuntimeException("Wrong path to the folder");

        File fileArtist = null;
        File fileRelease = null;
        File fileLabel = null;
        for (final File file : xmlFolder.listFiles()) {
            if (FilenameUtils.getExtension(file.getName()).equals("xml")) {
                if (file.getName().toLowerCase().contains("artists"))
                    fileArtist = file;
                if (file.getName().toLowerCase().contains("releases"))
                    fileRelease = file;
                if (file.getName().toLowerCase().contains("labels"))
                    fileLabel = file;
            }
        }
        if ((fileArtist == null) && (fileRelease == null))
            throw new RuntimeException("XML files not found");

        Connection con;
        try {
            con = DBManage.getDBConnect(databaseUsername, databaseUserPassword, databaseURL);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (fileArtist == null)
            logger.warn("Artist XML file not found");
        else {
            ParserArtist parserArtist = new ParserArtist(con);
            parserArtist.parse(fileArtist);
            idsArtist = parserArtist.getIds();
        }
        if (fileLabel == null)
            logger.warn("Label XML file not found");
        else {
            ParserLabel parserLabel = new ParserLabel(con);
            parserLabel.parse(fileLabel);
            idsLabel = parserLabel.getIds();
        }
        if (fileRelease == null)
            logger.warn("Release XML file not found");
        else {
            ParserRelease parserRelease = new ParserRelease(con);
            parserRelease.setIdsLabel(idsLabel);
            parserRelease.setIdsArtist(idsArtist);
            parserRelease.parse(fileRelease);
        }
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
