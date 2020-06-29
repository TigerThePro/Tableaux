package Model;

import Model.Operation.PropOperation;
import Model.Sentence.Sentence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Branch {

    public ArrayList<Sentence> sentenceList;
    /* Making to lists for operation, so there
    is no need for priority queue
    */
    public ArrayList<Sentence> todoList1;
    public ArrayList<Sentence> todoList2;

    public ArrayList<Sentence> simpleList;
    public ArrayList<Branch> extension;
    public boolean open;


    public Branch(ArrayList<Sentence> sentences) {
        sentenceList = new ArrayList<Sentence>();
        todoList1 = new ArrayList<Sentence>();
        todoList2 = new ArrayList<Sentence>();
        simpleList = new ArrayList<Sentence>();
        extension = new ArrayList<Branch>();
        open = true;
        processSentences(sentences);
        checkBranchOpen();
        if (open) {
            orderOperation();
        }
    }

    public Branch(Sentence sentence, ArrayList<Sentence> operations1, ArrayList<Sentence> operation2,
                  ArrayList<Sentence> simps) {
        sentenceList = new ArrayList<Sentence>();
        todoList1 = operations1;
        todoList2 = simps;
        extension = new ArrayList<Branch>();

        addSentence(sentence);

    }


    public void addSentence(Sentence sentence) {
        if (open) {
            sentenceList.add(sentence);
            if (!checkContra(sentence)) {
                addOperation(sentence);
            } else {
                open = false;
            }
        }
    }

//    public void extend(Node) {
//
//    }





    // Helper
    // Processes sentences,
    // updates sentenceList, todoList, simpleList
    // then if contradictions exists closes branch.

    public void processSentences(ArrayList<Sentence> sentences) {
        Iterator<Sentence> i = sentences.iterator();
        while (i.hasNext()) {
            Sentence temp =i.next();
            sentenceList.add(temp);
            if (temp.type == Structure.atomic ||
                    (temp.type == Structure.unary && temp.subsentence.get(0).type == Structure.atomic)) {
                simpleList.add(temp);
            }
        }
    }



    // Helper
    // Order operation
    public void checkBranchOpen() {
        Iterator<Sentence> i = sentenceList.iterator();
        while(i.hasNext()) {
            Sentence temp = i.next();
            if (checkContra(temp)) {
                open = false;
                break;
            }
        }
    }


    // Helper
    // Order Operation
    private void orderOperation() {
        Iterator<Sentence> i = sentenceList.iterator();
        while (i.hasNext()) {
            addOperation(i.next());
        }

    }




    // Helper
    // Check for contradiction for a simple sentence.
    private boolean checkContra(Sentence sen) {
        Iterator<Sentence> j = simpleList.iterator();
        if (sen.type == Structure.atomic) {
            while (j.hasNext()) {
                Sentence jSentence = j.next();
                if (jSentence.type == Structure.unary &&
                        sen.returnSentence() == jSentence.subsentence.get(0).returnSentence()){
                    return true;
                }
            }
        } else {
            while (j.hasNext()) {
                Sentence jSentence = j.next();
                if (sen.subsentence.get(0).returnSentence() == jSentence.returnSentence()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper
    // addOperaton
    private void addOperation(Sentence sentence) {
        int p = sentence.returnPriority();
        if (p == 1) {
            todoList1.add(sentence);
        } else if (p == 2) {
            todoList2.add(sentence);
        }
    }
}
