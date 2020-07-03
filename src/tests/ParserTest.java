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
    void testPSbasicIFF() {
        try {
            Sentence giffh = parser.parseSentence("G iff H");
            assertEquals("g iff h", giffh.returnSentence());
        } catch(Exception e) {
            fail("NO");
        }
    }

    @Test
    void testPSbasicAND() {
        try {
            Sentence kANDw = parser.parseSentence("k AnD W");
            assertEquals("k and w", kANDw.returnSentence());
        } catch (Exception e) {
            fail("Should not have exception");
        }
    }

    @Test
    void testPSbasicOR() {
        try {
            Sentence nORe = parser.parseSentence("N oR e");
            assertEquals("n or e", nORe.returnSentence());
        } catch (Exception e) {
            fail("Should not have exception");
        }
    }

    @Test
    void testPSbasicIMPLIES() {
        try {
            Sentence fIMPLIESs = parser.parseSentence("f implies s");
            assertEquals("f implies s", fIMPLIESs.returnSentence());
        } catch (Exception e) {
            fail("Should not have exception");
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
            Sentence x = parser.parseSentence("not (a and b)");
            assertEquals("not (a and b)", x.returnSentence());
        } catch (Exception e) {
//            System.out.println(e);
            fail(e);
        }
    }

    @Test
    void parentheses1() {
        try {
            String s = "(not p) implies q";
            Sentence x = parser.parseSentence(s);
            assertEquals(s, x.returnSentence());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void parentheses2() {
        try {
            String s = "m and (not (a and b))";
            Sentence x = parser.parseSentence(s);
            assertEquals(s, x.returnSentence());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void parentheses3() {
        try {
            String s = "((not u) and t) implies ((not (p or q)) or (not o))";
            Sentence x = parser.parseSentence(s);
            assertEquals(s, x.returnSentence());
        } catch (Exception e) {
            fail(e);
        }
    }


}
