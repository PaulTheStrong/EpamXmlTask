package test.com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.parsers.DomParser;
import com.epam.training.tasks.xml.parsers.XmlParser;

public class DomParserTest extends AbstractParserTest{
    @Override
    protected XmlParser getParser() {
        return new DomParser();
    }
}
