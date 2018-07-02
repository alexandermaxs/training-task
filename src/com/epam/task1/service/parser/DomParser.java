package com.epam.task1.service.parser;

import com.epam.task1.model.Toy;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger(DomParser.class);
    private String xmlPath;
    private DocumentBuilder documentBuilder;
    private List<Toy> toys;

    public DomParser(String xmlPath) {
        this.xmlPath = xmlPath;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Toy> parse() {
        Document document = null;
        try {
            document = documentBuilder.parse(new File(xmlPath));
        } catch (SAXException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        toys = new ArrayList<>();
        NodeList nodeList = root.getElementsByTagName("toy");
        Toy toy = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                toy = new Toy();
            }
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node cNode = childNodes.item(j);
                if (cNode instanceof Element) {
                    String content = cNode.getLastChild().getTextContent().trim();
                    switch (cNode.getNodeName()) {
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
                }
            }
            toys.add(toy);
        }
        return toys;
    }
}
