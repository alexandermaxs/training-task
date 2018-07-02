package com.epam.task1.service.parser;

import com.epam.task1.model.Toy;
import org.apache.log4j.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxParser implements Parser{

    private static final Logger LOGGER = Logger.getLogger(StaxParser.class);
    private XMLStreamReader reader;

    public StaxParser(String xmlPath) {
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
        List<Toy> toys = new ArrayList<>();
        Toy toy = null;
        String content = null;
        try {
            while (reader.hasNext()){
                int count = reader.next();
                switch (count) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("toy".equals(reader.getLocalName())) {
                            toy = new Toy();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        content = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "toy":
                                toys.add(toy);
                                break;
                            case "price":
                                toy.setPrice(Integer.parseInt(content));
                                break;
                            case "size":
                                toy.setSize(content);
                                break;
                            case "type":
                                toy.setType(content);
                                break;
                        }
                        break;
                }
            }
            return toys;
        } catch (XMLStreamException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
