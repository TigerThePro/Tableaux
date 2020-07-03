package tests;

import Model.Parser.Parser;
import Model.Sentence.Sentence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    void testPSbasicAtomic() {
        try {
            Sentence p = parser.parseSentence("P");
            assertEquals("p", p.returnSentence());
        } catch (Exception e) {
            fail("NO");
        }
    }

    @Test
    void testPSbasicNegate() {
        try{
            Sentence notA = parser.parseSentence("not A");
            assertEquals("not a", notA.returnSentence());
        } catch (Exception e) {
            fail("NO");
        }
    }

    @Test
    void testPSbasicBin() {
        try {
            Sentence giffh = parser.parseSentence("G iff H");
            assertEquals("g iff h", giffh.returnSentence());
        } catch(Exception e) {
            fail("NO");
        }
    }

    @Test
    void basicFail() {
        try {
            Sentence pnotq = parser.parseSentence("p not q");
            fail("Should not pass");
        } catch (Exception e) {
            // expected
        }
    }

    @Test
    void basicParentheses() {
        try {
            Sentence x = parser.parseSentence("not (a)");
        } catch (Exception e) {
            fail(e);
        }
    }
}
