package Model;

import Model.Sentence.Sentence;

import java.util.ArrayList;
import java.util.Iterator;

public class Tableaux {

    public Tableaux () {
    }


    public Branch prepareTableaux(ArrayList<Sentence> sentences, Sentence conclusion) {
        sentences.add(new Sentence("not", conclusion));
        Branch branch = new Branch(sentences);
//        extend(branch);
        return branch;
    }

    // Return true if tree closes
    public boolean extend(Branch branch) {
        if (!branch.open) {
//            System.out.println("WHAT!!");
            return true;
        }
        Iterator<Sentence> i = branch.todoList1.iterator();
        while (i.hasNext()) {
            extend1(i.next(), branch);
//            i.remove();
            if (!branch.open) {
                return true;
            }
        }
        if (branch.todoList2.size() > 0) {
            extend2(branch.todoList2.get(0), branch);
            return (extend(branch.left) && extend(branch.right));
        }
        return false;

    }


    // Helper
    // extending one branch
    private void extend1(Sentence sentence, Branch branch) {
        Sentence subsentence = sentence.subsentence.get(0);
        switch (sentence.returnOperation()) {
            case and:
                branch.addSentence(sentence.subsentence.get(0));
                branch.addSentence(sentence.subsentence.get(1));
                break;
            case notor:
                branch.addSentence(new Sentence("not", subsentence.subsentence.get(0)));
                branch.addSentence(new Sentence("not", subsentence.subsentence.get(1)));
                break;
            case notimplies:
                branch.addSentence(subsentence.subsentence.get(0));
                branch.addSentence(new Sentence("not", subsentence.subsentence.get(1)));
                break;
            case notnot:
                branch.addSentence(subsentence.subsentence.get(0));
                break;
            default:
        }
        sentence.check();
    }


    // Helper
    // extend two branch
    private void extend2(Sentence sentence, Branch branch) {
//        System.out.println(sentence.returnSentence());
        branch.todoList2.remove(0);
        ArrayList<Sentence> leftSentences = new ArrayList<Sentence>();
        ArrayList<Sentence> rightSentences = new ArrayList<Sentence>();
        Sentence subsentence = sentence.subsentence.get(0);
        switch (sentence.returnOperation()) {
            case implies:
                leftSentences.add(new Sentence("not", sentence.subsentence.get(0)));
                rightSentences.add(sentence.subsentence.get(1));
                break;

            case or:
                leftSentences.add(sentence.subsentence.get(0));
                rightSentences.add(sentence.subsentence.get(1));
                break;

            case iff:
                leftSentences.add(sentence.subsentence.get(0));
                leftSentences.add(sentence.subsentence.get(1));
                rightSentences.add(new Sentence("not", sentence.subsentence.get(0)));
                rightSentences.add(new Sentence("not", sentence.subsentence.get(1)));
                break;

            case notand:
                leftSentences.add(new Sentence("not", subsentence.subsentence.get(0)));
                rightSentences.add(new Sentence("not", subsentence.subsentence.get(1)));
                break;

            case notiff:
                leftSentences.add(subsentence.subsentence.get(0));
                leftSentences.add(new Sentence("not", subsentence.subsentence.get(1)));
                rightSentences.add(new Sentence("not", subsentence.subsentence.get(0)));
                rightSentences.add(subsentence.subsentence.get(1));
                break;

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
