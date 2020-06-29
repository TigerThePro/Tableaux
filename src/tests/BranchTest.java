package tests;

import Model.Branch;
import Model.Sentence.Sentence;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BranchTest {

    private Sentence s1 = new Sentence("p");
    private Sentence s2 = new Sentence("q");
    private Sentence s3 = new Sentence("not", s1);
    private Sentence s4 = new Sentence(s1,"or", s2);

    private ArrayList<Sentence> sentenceList1 = new ArrayList<Sentence>();
    private ArrayList<Sentence> sentenceList2 = new ArrayList<Sentence>();

    @Test
    void testConstructor1() {
        sentenceList1.add(s1);
//        sentenceList2
//        Branch branch = new Bra
    }


}