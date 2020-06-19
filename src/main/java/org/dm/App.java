package org.dm;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

            String pathToXMLFolder = cmd.getOptionValue("pathToXMLFolder");
            String databaseUsername = cmd.getOptionValue("databaseUsername");
            String databaseUserPassword = cmd.getOptionValue("databaseUserPassword");
            String databaseURL = cmd.getOptionValue("databaseURL");

            logger.info(pathToXMLFolder);
            logger.info(databaseUsername);
            logger.info(databaseUserPassword);
            logger.info(databaseURL);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            new HelpFormatter().printHelp("discogsXMlToMySql", options);
            throw new RuntimeException("Wrong arguments");
        }
    }
}
