package org.dm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dm.model.DC_Entity;
import org.dm.model.DC_Label;
import org.dm.model.JDBC_Response;
import org.dm.model.Seq;
import org.dm.repo.JDBC_Label;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserLabel extends Parser {
    private static final Logger logger = LogManager.getLogger(ParserLabel.class);
    //----------------------------------------------------------------------------------
    public ParserLabel() {
        handler = regHandler();
    }
    //----------------------------------------------------------------------------------
    public ParserLabel(Connection connection) {
        handler = regHandler();
        handler.connection = connection;
    }
    //----------------------------------------------------------------------------------
    @Override
    public XMLHandler regHandler() {
        return new XMLHandlerLabel();
    }
    //----------------------------------------------------------------------------------
    public static class XMLHandlerLabel extends XMLHandler {
        public Map<Integer, Integer> idsLabel = new HashMap<>();

        DC_Label label;
        Seq seqLabel = new Seq();
        boolean bId;
        boolean bName;
        boolean bSublabels;

        JDBC_Label jdbcLabel = new JDBC_Label();

        List<DC_Label> lLabel = new ArrayList<>();
        //----------------------------------------------------------------------------------
        @Override
        public void truncate() {
            JDBC_Response respTruncate = jdbcLabel.truncateAll(connection);
            if (!respTruncate.bSuccess) {
                throw new RuntimeException("truncate label msg:" + respTruncate.sMessage);
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("label") && !bSublabels) {
                label = new DC_Label();
                label.id = seqLabel.getNext();
            } else if (qName.equalsIgnoreCase("id") && !bSublabels) {
                bId = true;
            } else if (qName.equalsIgnoreCase("name") && !bSublabels) {
                bName = true;
            } else if (qName.equalsIgnoreCase("sublabels")) {
                bSublabels = true;
            }
            stringBuilder = new StringBuilder();
        }
        //----------------------------------------------------------------------------------
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (bId) {
                label.idDC = Integer.parseInt(stringBuilder.toString());
                bId = false;
            } else if (bName) {
                label.setsName(stringBuilder.toString());
                bName = false;
            } else if (bSublabels && qName.equalsIgnoreCase("sublabels")) {
                bSublabels = false;
            } else if (qName.equalsIgnoreCase("label") && !bSublabels) {
                lLabel.add(label);
                idsLabel.put(label.idDC, label.id);
                num++;
                if (bId
                        || bName
                        || bSublabels)
                    throw new RuntimeException("label parse error " + label.idDC);

                if ((num % 100000 == 0) & !bTest) {
                    writeToDB();
                    clearLists();
                    logger.info(num + " labels done");
                }
            }
        }
        //----------------------------------------------------------------------------------
        @Override
        public void insert() {
            JDBC_Response respInsertLabel = jdbcLabel.insert(lLabel, connection);
            if (!respInsertLabel.bSuccess)
                throw new RuntimeException("Label msg:" + respInsertLabel.sMessage);
        }
        //----------------------------------------------------------------------------------
        @Override
        public void clearLists() {
            lLabel = new ArrayList<>();
        }
        //----------------------------------------------------------------------------------
        @Override
        public List<? extends DC_Entity> getResult() {
            return lLabel;
        }
    }
    //----------------------------------------------------------------------------------
    public Map<Integer, Integer> getIds() {
        return ((XMLHandlerLabel)getHandler()).idsLabel;
    }
}
