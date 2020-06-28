package Model;

import Model.Sentence.Sentence;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Branch {

    public ArrayList<Sentence> sentenceList;
    public ArrayList<Sentence> todoList;
    public ArrayList<Sentence> simpleList;
    public ArrayList<Branch> extension;
    public boolean open;


    public Branch(ArrayList<Sentence> sentences) {
        sentenceList = new ArrayList<Sentence>();
        todoList = new ArrayList<Sentence>();
        simpleList = new ArrayList<Sentence>();
        extension = new ArrayList<Branch>();
        open = true;
        processSentences(sentences);


    }


//    public void addSentence






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
        Iterator<Sentence>
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


}
