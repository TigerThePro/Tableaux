package Model;

import Model.Sentence.Sentence;

import java.util.ArrayList;
import java.util.Iterator;

public class Tableaux {

    public Tableaux (ArrayList<Sentence> sentences, Sentence conclusion) {
        sentences.add(new Sentence("not", conclusion));
        Branch branch = new Branch(sentences);
    }



//    public void extend() {
//        Iterator<Sentence> i = todoList1.iterator();
//        while (i.hasNext()) {
//            extend1(i.next());
//            i.remove();
//            if (!open) {
//                break;
//            }
//        }
//        Iterator<Sentence> j = todoList2.iterator();
//        while (j.hasNext()) {
//
//        }
//    }

//
//    // Helper
//    // extending one branch
//    private void extend1(Sentence sentence) {
//        switch (sentence.returnOperation()) {
//            case and:
//                addSentence(sentence.subsentence.get(0));
//                addSentence(sentence.subsentence.get(1));
//            case notor:
//                addSentence(new Sentence("not", sentence.subsentence.get(0)));
//                addSentence(new Sentence("not", sentence.subsentence.get(1)));
//            case notimplies:
//                addSentence(sentence.subsentence.get(0));
//                addSentence(new Sentence("not", sentence.subsentence.get(1)));
//            case notnot:
//                addSentence(sentence.subsentence.get(0).subsentence.get(0));
//            default:
//        }
//        sentence.check();
//    }
//
//
//    // Helper
//    // extend two branch
//    private void extend2(Sentence sentence) {
//        switch (sentence.returnOperation()) {
//            case implies:
//                Branch left = new Branch(
//                        new Sentence("not", sentence.subsentence.get(0)),
//                        todoList2,
//                        simpleList
//                );
//                Branch
//            case or:
//            case iff:
//            case notand:
//            case notiff:
//        }
//    }
}
