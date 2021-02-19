package test.com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.parsers.SaxParser;
import com.epam.training.tasks.xml.parsers.XmlParser;

public class SaxParserTest extends AbstractParserTest{

    @Override
    protected XmlParser getParser() {
        return new SaxParser();
    }
}
