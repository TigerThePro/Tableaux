package tests;

import Model.Branch;
import Model.Sentence.Sentence;
import Model.Tableaux;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableauxTest {

    Tableaux table = new Tableaux();

    Sentence p = new Sentence("p");
    Sentence q = new Sentence("q");
    Sentence r = new Sentence("r");

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

        System.out.println(branch.todoList1);

        assertEquals("[p and q, p, q]", branch.returnBranch());
    }

    // not and
    @Test
    void testNotAnd() {
        sentences.add(notpandq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[not (p and q) [not p] [not q]]", branch.returnBranch());
    }

    // or
    @Test
    void testOr() {
        sentences.add(porq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[p or q [p] [q]]", branch.returnBranch());
    }

    // not or
    @Test
    void testNotOr() {
        sentences.add(notporq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[not (p or q), not p, not q]", branch.returnBranch());
    }

    // Implies
    @Test
    void testImplies() {
        sentences.add(pimpliesq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[p implies q [not p] [q]]", branch.returnBranch());
    }

    // Not implies
    @Test
    void testNotImplies() {
        sentences.add(notpimpliesq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[not (p implies q), p, not q]", branch.returnBranch());
    }

    // iff
    @Test
    void testIff() {
        sentences.add(piffq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[p iff q [p, q] [not p, not q]]", branch.returnBranch());
    }

    // not iff
    @Test
    void testNotIff() {
        sentences.add(notpiffq);
        branch = new Branch(sentences);
        table.extend(branch);

        assertEquals("[not (p iff q) [p, not q] [not p, q]]", branch.returnBranch());
    }

    // Test Make Tableaux

    @Test
    void testSimpleClosedTree() {
        sentences.add(p);
        branch = table.prepareTableaux(sentences, p);

        assertEquals(true, table.extend(branch));
    }


    @Test
    void testClosedTree() {
        sentences.add(porq);
        sentences.add(new Sentence(p, "implies", r));
        sentences.add(new Sentence(q, "implies", r));
        branch = table.prepareTableaux(sentences, r);

//        assertEquals(true, table.extend(branch));
        table.extend(branch);
        System.out.println(branch.returnBranch());
//        System.out.println(branch.left.todoList2.size());
//        System.out.println(branch.left.open);
//        System.out.println(branch.right.todoList2.size());
//        System.out.println(branch.right.open);
        assertEquals("[p or q, p implies r, q implies r, not r " +
                "[p [not p] [r]] " +
                "[q [not p [not q] [r]] [r]]]", branch.returnBranch());
    }

    @Test
    void testOpenTree() {
        Sentence s = new Sentence("s");
        Sentence rors = new Sentence(r, "or", s);
        Sentence pimpliesr = new Sentence(p, "implies", r);
        sentences.add(pimpliesq);
        sentences.add(new Sentence(q, "implies", rors));
        branch = table.prepareTableaux(sentences, pimpliesr);

        assertEquals(false, table.extend(branch));

        assertEquals(
                "[p implies q, q implies (r or s), not (p implies r), p, not r " +
                        "[not p] " +
                        "[q [not q] [r or s [r] [s]]]]",
                branch.returnBranch()
        );

    }
}
