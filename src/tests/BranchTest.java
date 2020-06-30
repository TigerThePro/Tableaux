package tests;

import Model.Branch;
import Model.Operation.PropOperation;
import Model.Sentence.Sentence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BranchTest {

    private Sentence s1 = new Sentence("p");
    private Sentence s2 = new Sentence("q");
    private Sentence s3 = new Sentence("not", s1);
    private Sentence s4 = new Sentence(s1,"or", s2);
    private Sentence s5 = new Sentence("not", s3);
    private Sentence s6 = new Sentence("not", s4);

    private ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();

    // two atomic sentence
    // p , q
    @Test
    void testConstructor1() {
        sentenceList.add(s1);
        sentenceList.add(s2);
        Branch branch = new Branch(sentenceList);
        assertEquals(true, branch.open);
        assertEquals(2, branch.sentenceList.size());
        assertEquals(0, branch.todoList1.size());
        assertEquals(0, branch.todoList2.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(sentenceList.get(i).returnSentence(),
                    branch.simpleList.get(i).returnSentence());
            assertEquals(sentenceList.get(i).returnSentence(),
                    branch.sentenceList.get(i).returnSentence());
        }
        assertEquals("[p, q]", branch.returnBranch());
    }

    // p, not p
    @Test
    void testConstructor2() {
        sentenceList.add(s1);
        sentenceList.add(s3);
        Branch branch = new Branch(sentenceList);;
        assertEquals(false, branch.open);
        assertEquals(2, branch.sentenceList.size());
        assertEquals(0, branch.todoList1.size());
        assertEquals(0, branch.todoList2.size());;
        for (int i = 0; i < 1; i++) {
            assertEquals(sentenceList.get(i).returnSentence(),
                    branch.simpleList.get(i).returnSentence());
        }
        assertEquals("[p, not p]", branch.returnBranch());
    }

    // not not p
    @Test
    void testConstructor3() {
        sentenceList.add(s5);
        Branch branch = new Branch(sentenceList);
        assertEquals(true, branch.open);
        assertEquals(1, branch.sentenceList.size());
        assertEquals(1, branch.todoList1.size());
        assertEquals(0, branch.todoList2.size());;
        assertEquals(0, branch.simpleList.size());
//        assertEquals("p", branch.simpleList.get(0).returnSentence());
//        assertEquals(true, branch.sentenceList.get(0).check);

        assertEquals("[not (not p)]", branch.returnBranch());
        assertEquals(PropOperation.notnot, branch.todoList1.get(0).returnOperation());
    }

    // p, p or q, not (p or q)
    @Test
    void testConstructor4() {
        sentenceList.add(s1);
        sentenceList.add(s4);
        sentenceList.add(s6);
        Branch branch = new Branch(sentenceList);
        assertEquals(true, branch.open);
        assertEquals(3, branch.sentenceList.size());
        assertEquals(1, branch.todoList1.size());
        assertEquals(1, branch.todoList2.size());
        assertEquals(1, branch.simpleList.size());

        assertEquals("[p, p or q, not (p or q)]", branch.returnBranch());
        assertEquals(PropOperation.notor, branch.todoList1.get(0).returnOperation());

    }




}