package org.dm;

import org.dm.model.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {
    Connection connection;
    Boolean bTest = false;
    int num = 0;
    StringBuilder stringBuilder = null;
    //----------------------------------------------------------------------------------
    @Override
    public void startDocument() throws SAXException {
        try {
            if (!bTest) {
                truncate();
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------
    @Override
    public void endDocument() throws SAXException {
        if (!bTest) writeToDB();
    }
    //----------------------------------------------------------------------------------
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(new String(ch, start, length));
    }
    //----------------------------------------------------------------------------------
    public void truncate() {

    }
    //----------------------------------------------------------------------------------
    public void writeToDB() {
        try {
            insert();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------
    public void insert() {

    }
    //----------------------------------------------------------------------------------
    public void clearLists() {

    }
    //----------------------------------------------------------------------------------
    public List<? extends DC_Entity> getResult() {
        return new ArrayList<>();
    }
}
