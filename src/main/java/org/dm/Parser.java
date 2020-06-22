package org.dm;

import org.dm.model.DC_Entity;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.util.List;

public class Parser {
    //----------------------------------------------------------------------------------
    public void parser(Connection connection, File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = getHandler();
            handler.connection = connection;
            parser.parse(file, handler);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    //----------------------------------------------------------------------------------
    public List<? extends DC_Entity> parser(String sXML) {
        List<? extends DC_Entity> lResult;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = getHandler();
            handler.bTest = true;
            parser.parse(new ByteArrayInputStream(sXML.getBytes()), handler);
            lResult = handler.getResult();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return lResult;
    }
    //----------------------------------------------------------------------------------
    public XMLHandler getHandler() {
        return null;
    }
}
