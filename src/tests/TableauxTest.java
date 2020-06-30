package tests;

import Model.Branch;
import Model.Sentence.Sentence;
import jdk.jshell.SnippetEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Model.Tableaux;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TableauxTest {

    Tableaux table = new Tableaux();

    Sentence p = new Sentence("p");
    Sentence q = new Sentence("q");

    Sentence pandq = new Sentence(p, "and", q);
    Sentence notpandq = new Sentence("not", pandq);

    Sentence porq = new Sentence(p, "or", q);
    Sentence notporq = new Sentence("not", porq);

    Sentence pimpliesq = new Sentence(p, "implies", q);
    Sentence notpimpliesq = new Sentence("not", pimpliesq);

    Sentence piffq = new Sentence(p, "iff", q);
    Sentence notpiffq = new Sentence("not", piffq);


    ArrayList<Sentence> sentences = new ArrayList<Sentence>();

    Branch branch;

    // not not
    @Test
    void testNotNot() {
        sentences.add(new Sentence("not", new Sentence("not", p)));
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[not (not p), p]", branch.returnBranch());
    }

    // and
    @Test
    void testAnd() {
        sentences.add(pandq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[p and q, p, q]", branch.returnBranch());
    }

    // not and
    @Test
    void testNotAnd() {
        sentences.add(notpandq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[not (p and q) [p] [q]]", branch.returnBranch());
    }

    // or
    @Test
    void testOr() {
        sentences.add(porq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[p or q [p] [q]]", branch.returnBranch());
    }
}
