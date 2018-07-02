package com.epam.task1.service.parser;

import com.epam.task1.model.Toy;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SaxParser implements Parser {
    private static final Logger LOGGER = Logger.getLogger(SaxParser.class);
    private XMLStreamReader reader;
    private String xmlPath;

    public SaxParser(String xmlPath){
        this.xmlPath = xmlPath;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream xmlSource = new FileInputStream(xmlPath);
            reader = inputFactory.createXMLStreamReader(xmlSource);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (XMLStreamException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Toy> parse() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            saxParser.parse(xmlPath, saxHandler);
            return saxHandler.getToys();
        } catch (SAXException e) {
            LOGGER.error(e);
            return null;
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
            return null;
        } catch (IOException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
