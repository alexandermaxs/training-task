package com.epam.task1.service;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class SchemeValidation {
    private static final Logger LOGGER = Logger.getLogger(SchemeValidation.class);

    public boolean validate(String xsd, String xml){
        try {
            SchemaFactory xmlFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = xmlFactory.newSchema(new File(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xml)));
        } catch (IOException e) {
            LOGGER.error(e);
            return false;
        } catch (SAXException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
