package org.dm;

import org.dm.model.DC_Entity;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.util.List;

public class Parser {
    XMLHandler handler;
    //----------------------------------------------------------------------------------
    public Parser() {
        handler = regHandler();
    }
    //----------------------------------------------------------------------------------
    public Parser(Connection connection) {
        handler = regHandler();
        handler.connection = connection;
    }
    //----------------------------------------------------------------------------------
    public void parse(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, this.handler);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    //----------------------------------------------------------------------------------
    public List<? extends DC_Entity> parse(String sXML) {
        List<? extends DC_Entity> lResult;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            handler.bTest = true;
            parser.parse(new ByteArrayInputStream(sXML.getBytes()), handler);
            lResult = handler.getResult();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return lResult;
    }
    //----------------------------------------------------------------------------------
    public XMLHandler regHandler() {
        return null;
    }
    //----------------------------------------------------------------------------------
    public XMLHandler getHandler() {
        return handler;
    }
}
