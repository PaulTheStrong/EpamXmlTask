package test.com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.parsers.JaxbParser;
import com.epam.training.tasks.xml.parsers.XmlParser;

public class JaxbParserTest extends AbstractParserTest{
    @Override
    protected XmlParser getParser() {
        return new JaxbParser();
    }
}
