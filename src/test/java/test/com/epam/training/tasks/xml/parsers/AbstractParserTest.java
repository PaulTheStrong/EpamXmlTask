package test.com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.exceptions.XmlParseException;
import com.epam.training.tasks.xml.entities.Deposit;
import com.epam.training.tasks.xml.entities.TermDeposit;
import com.epam.training.tasks.xml.parsers.XmlParser;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.epam.training.tasks.xml.entities.Currency.*;

public abstract class AbstractParserTest {

    private XmlParser parser = getParser();

    protected abstract XmlParser getParser();

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String xmlPath = "src/main/resources/xml/deposits.xml";
    private static final Double EPS = 0.001;
    private static Deposit[] EXPECTED;

    private static final Logger LOGGER = Logger.getRootLogger();

    static {
        try {
            EXPECTED = new Deposit[] {
                    new Deposit("AD7654321", "Belarus", "BSB Bank", "First deposit", 1000, 5, BYN),
                    new Deposit("PM3221337", "USA", "Cool American Bank", "", 40000, 12.5, USD),
                    new TermDeposit("AB2312133", "USA", "Less American Bank", "The profitability less than in Cool American Bank", 10000, 5.3, EUR, DATE_FORMAT.parse("2021-06-12"), DATE_FORMAT.parse("2021-09-12")),
                    new TermDeposit("MT3211233", "Belarus", "Dungeon bank", "Three hundred bucks bank deposit", 300, 100, USD, DATE_FORMAT.parse("2001-01-01"), DATE_FORMAT.parse("2021-01-01"))
            };
        } catch (ParseException e) {
            LOGGER.error("Parse exception: " + e);
        }
    }

    @Test
    public void testXmlParser() throws XmlParseException {
        //when
        List<Deposit> actual = parser.parse(xmlPath);
        Deposit[] actualArray = new Deposit[4];
        actual.toArray(actualArray);

        //then
        Assert.assertEquals(4, actual.size());
        assertDepositEquals(EXPECTED[0], actualArray[0]);
        assertDepositEquals(EXPECTED[1], actualArray[1]);
        assertDepositEquals((TermDeposit) EXPECTED[2], (TermDeposit) actualArray[2]);
        assertDepositEquals((TermDeposit) EXPECTED[3], (TermDeposit) actualArray[3]);
    }

    private void assertDepositEquals(Deposit expected, Deposit actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCountry(), actual.getCountry());
        Assert.assertEquals(expected.getBankName(), actual.getBankName());
        Assert.assertEquals(expected.getInfo(), actual.getInfo());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getAmount(), actual.getAmount(), EPS);
        Assert.assertEquals(expected.getProfitability(), actual.getProfitability(), EPS);
    }

    private void assertDepositEquals(TermDeposit expected, TermDeposit actual) {
        assertDepositEquals(expected, (Deposit) actual);
        Assert.assertEquals(expected.getStartDate(), actual.getStartDate());
        Assert.assertEquals(expected.getEndDate(), actual.getEndDate());
    }

}
