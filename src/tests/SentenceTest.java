package tests;

import Model.Operation.Operation;
import Model.Operation.PropOperation;
import Model.Sentence.Sentence;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SentenceTest {

    private Sentence s1 = new Sentence("p");
    private Sentence s2 = new Sentence("q");
    private Sentence s3 = new Sentence(s1,"or", s2);
    private Sentence s4 = new Sentence(s1, "implies", s3);
    private Sentence s5 = new Sentence("not", s3);
    private Sentence s6 = new Sentence(s3, "and", s2);
    private Sentence s7 = new Sentence(s6, "iff", s2);
    private Sentence s8 = new Sentence("not", s4);

    private String[] sentenceStrings = {
            "p", "q", "p or q", "p implies (p or q)", "not (p or q)", "(p or q) and q",
            "((p or q) and q) iff q", "not (p implies (p or q))"};

    private PropOperation[] sentenceOperation = {
            PropOperation.none, PropOperation.none, PropOperation.or, PropOperation.implies,
            PropOperation.notor, PropOperation.and, PropOperation.iff, PropOperation.notimplies };

    private boolean[] sentenceChecks = {false, true, true, false, false, false, true, true};

    private int[] sentencePriority = {0, 0, 2, 2, 1, 1, 2, 1};

    private Sentence[] sentences = {s1, s2, s3, s4, s5, s6, s7, s8};

    @Test
    void testreturnSentence() {
        for (int i = 0; i < 8; i++) {
            if (sentenceChecks[i]) {
                sentences[i].check();
                assertEquals(sentenceChecks[i], sentences[i].check);
            }
        }
    }

    @Test
    void testreturnString() {
        for (int i = 0; i < 8; i++) {
            assertEquals(sentenceStrings[i], sentences[i].returnSentence());
        }
    }

    @Test
    void testreturnOperation() {
        for (int i = 0; i < 8; i++) {
            assertEquals(sentenceOperation[i], sentences[i].returnOperation());
        }
    }

    @Test
    void testreturnPriority() {
        for (int i = 0; i < 8; i++) {
            assertEquals(sentencePriority[i], sentences[i].returnPriority());
        }
    }
}