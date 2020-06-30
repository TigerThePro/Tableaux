package Model;

import Model.Sentence.Sentence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Tableaux {

    public Tableaux () {
    }


    public Branch makeTableaux(ArrayList<Sentence> sentences, Sentence conclusion) {
        sentences.add(new Sentence("not", conclusion));
        Branch branch = new Branch(sentences);
        extend(branch);
        return branch;
    }

    // Return true if tree closes
    public boolean extend(Branch branch) {
        Iterator<Sentence> i = branch.todoList1.iterator();
        while (i.hasNext()) {
            extend1(i.next(), branch);
            i.remove();
            if (!branch.open) {
                return true;
            }
        }
        if (branch.open && branch.todoList2.size() > 0) {
                extend2(branch.todoList2.get(0), branch);
                if (extend(branch.left) && extend(branch.right)) {
                    return true;
                } else {
                    return false;
            }
        }
        return false;

    }


    // Helper
    // extending one branch
    private void extend1(Sentence sentence, Branch branch) {
        switch (sentence.returnOperation()) {
            case and:
                branch.addSentence(sentence.subsentence.get(0));
                branch.addSentence(sentence.subsentence.get(1));
            case notor:
                branch.addSentence(new Sentence("not", sentence.subsentence.get(0)));
                branch.addSentence(new Sentence("not", sentence.subsentence.get(1)));
            case notimplies:
                branch.addSentence(sentence.subsentence.get(0));
                branch.addSentence(new Sentence("not", sentence.subsentence.get(1)));
            case notnot:
                branch.addSentence(sentence.subsentence.get(0).subsentence.get(0));
            default:
        }
        sentence.check();
    }


    // Helper
    // extend two branch
    private void extend2(Sentence sentence, Branch branch) {
        branch.todoList2.remove(0);
        ArrayList<Sentence> leftSentences = new ArrayList<Sentence>();
        ArrayList<Sentence> rightSentences = new ArrayList<Sentence>();
        switch (sentence.returnOperation()) {
            case implies:
                leftSentences.add(new Sentence("not", sentence.subsentence.get(0)));
                rightSentences.add(sentence.subsentence.get(1));

            case or:
                leftSentences.add(sentence.subsentence.get(0));
                rightSentences.add(sentence.subsentence.get(1));

            case iff:
                leftSentences.add(sentence.subsentence.get(0));
                leftSentences.add(sentence.subsentence.get(1));
                rightSentences.add(new Sentence("not", sentence.subsentence.get(0)));
                rightSentences.add(new Sentence("not", sentence.subsentence.get(1)));

            case notand:
                leftSentences.add(new Sentence("not", sentence.subsentence.get(0)));
                rightSentences.add(new Sentence("not", sentence.subsentence.get(0)));

            case notiff:
                leftSentences.add(sentence.subsentence.get(0));
                leftSentences.add(new Sentence("not", sentence.subsentence.get(1)));
                rightSentences.add(new Sentence("not", sentence.subsentence.get(0)));
                rightSentences.add(sentence.subsentence.get(1));

            default:
        }
        makeChildren(leftSentences, rightSentences, branch);
    }

    // Helper
    // children maker
    private void makeChildren(ArrayList<Sentence> leftSentences, ArrayList<Sentence> rightSentences, Branch branch) {

        Branch left = new Branch(
                leftSentences,
                branch.todoList2,
                branch.simpleList
        );
        Branch right = new Branch(
                rightSentences,
                branch.todoList2,
                branch.simpleList
        );
        branch.addChild(left, right);
    }
}
