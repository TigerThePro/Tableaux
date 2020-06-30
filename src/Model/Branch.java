package Model;

import Model.Sentence.Sentence;

import java.util.ArrayList;
import java.util.Iterator;

public class Branch {

    public ArrayList<Sentence> sentenceList;
    /* Making to lists for operation, so there
    is no need for priority queue
    */
    public ArrayList<Sentence> todoList1;
    public ArrayList<Sentence> todoList2;

    public ArrayList<Sentence> simpleList;

    public Branch left;
    public Branch right;

    public boolean open;


    public Branch(ArrayList<Sentence> sentences) {
        sentenceList = new ArrayList<Sentence>();
        todoList1 = new ArrayList<Sentence>();
        todoList2 = new ArrayList<Sentence>();
        simpleList = new ArrayList<Sentence>();

        left = null;
        right = null;

        open = true;
        processSentences(sentences);
    }

    public Branch(ArrayList<Sentence> sentences, ArrayList<Sentence> operation2,
                  ArrayList<Sentence> simps) {
        sentenceList = new ArrayList<Sentence>();
        todoList1 = new ArrayList<Sentence>();
        todoList2 = (ArrayList<Sentence>)operation2.clone();
        simpleList = (ArrayList<Sentence>)simps.clone();

        left = null;
        right = null;

        open = true;
        processSentences(sentences);

    }


    public void addSentence(Sentence sentence) {
        if (open) {
            sentenceList.add(sentence);
            if (sentence.type == Structure.atomic ||
                    (sentence.type == Structure.unary && sentence.subsentence.get(0).type == Structure.atomic)) {
                if (!checkContra(sentence) && !simpleList.contains(sentence)) {
                    simpleList.add(sentence);
                }
            } else {
                addOperation(sentence);
            }
        }
    }

    public void addChild(Branch left, Branch right) {
        this.left = left;
        this.right = right;
    }

    public String returnBranch() {
        String thisNode = "[";
        for (int i = 0; i < sentenceList.size(); i++) {
            if (i != 0) {
                thisNode = thisNode + ", " +  sentenceList.get(i).returnSentence();
            } else {
                thisNode = thisNode + sentenceList.get(i).returnSentence();
            }
        }
        if (left != null && right != null) {
            thisNode = thisNode + " " + left.returnBranch() + " " + right.returnBranch();
        }
        return thisNode + "]";

    }



    // Helper
    // Processes sentences,
    // updates sentenceList, todoList, simpleList
    // then if contradictions exists closes branch.

    private void processSentences(ArrayList<Sentence> sentences) {
        for (int i = 0; i < sentences.size(); i++) {
            Sentence temp =sentences.get(i);
            addSentence(temp);

        }
    }



    // Helper
    // Check for contradiction for a simple sentence.
    // return true if it's a contradiction
    private boolean checkContra(Sentence sen) {
        System.out.println(sen.returnSentence());
            Iterator<Sentence> j = simpleList.iterator();
            if (sen.type == Structure.atomic) {
                while (j.hasNext()) {
                    Sentence jSentence = j.next();
                    if (jSentence.returnSentence().equals("not " + sen.returnSentence())) {
                        open = false;
                        return true;
                    }
                }
            } else if (sen.type == Structure.unary && sen.subsentence.get(0).type == Structure.atomic) {
                while (j.hasNext()) {
                    Sentence jSentence = j.next();
                    if (sen.returnSentence().equals("not " + jSentence.returnSentence())) {
                        open = false;
                        return true;
                    }
                }
            }
//        }
        return false;
    }

    // Helper
    // addOperation
    private void addOperation(Sentence sentence) {
        int p = sentence.returnPriority();
        if (p == 1) {
            todoList1.add(sentence);
        } else if (p == 2) {
            todoList2.add(sentence);
        }
    }
}
