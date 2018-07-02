package com.epam.task1.service.parser;

import com.epam.task1.model.Toy;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private List<Toy> toys = new ArrayList<>();
    private Toy toy;
    private String content;

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes){
        if ("toy".equals(qName)) {
            toy = new Toy();
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName){
        switch (qName) {
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
    }

    @Override
    public void characters ( char[] ch, int start, int length){
        content = String.copyValueOf(ch, start, length).trim();
    }

    public List<Toy> getToys() {
        return toys;
    }
}
