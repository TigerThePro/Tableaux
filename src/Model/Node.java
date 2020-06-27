package Model;

import Model.Sentence.Sentence;

import java.util.ArrayList;
import java.util.List;

public class Node {
    // Node has a list of sentence in the order added
    // anoter list of sentence in the order of priority
    //       1 comes before 2, 0 NOT in the list
    // has children
    // children can be added
    // has a list of simmple sentence, (atomic and negation atomic)
    // is either open or closed, a closed one cannot have children

    public List<Sentence> sentenceList;
    public List<Sentence> sentenceOperation;
    public List<Sentence> simpleSentencs;
    public List<Node> children;
    public boolean open;

    public Node(List<Sentence> simpleSentence) {
        sentenceList = new ArrayList<Sentence>();

    }
}
