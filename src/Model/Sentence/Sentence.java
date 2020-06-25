package Model.Sentence;
import Model.Operation.Operation;
import Model.Operation.PropOperation;
import Model.Structure;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


// Sentence is either a sentence or operator and subsentences
// has a type, subformula and check
// this should work as propositional sentence

public class Sentence {


//    public boolean atomic;
    public Structure type;
    public List<Sentence> subsentence;
    public String operator;
    public String atomicSentence;
    public boolean check;


    // To create atomic sentence eg: p
    public Sentence(String input) {
//        this.atomic = true;
        this.type = Structure.atomic;
        atomicSentence = input;
        subsentence = null;
        operator = null;
        check = false;
    }

    // To make sentence in the form of oper A, where p is a sentence
    public Sentence(String opr, Sentence sentence) {
//        atomic = false;
        this.type = Structure.unary;
        atomicSentence = null;
        operator = opr;
        this.subsentence = new ArrayList<Sentence>();
        this.subsentence.add(sentence);
        check = false;
    }

    // To make sentence in the for of A oper B, where A, B are sentences
    public Sentence(Sentence sentence1, String opr, Sentence sentence2) {
//        atomic = false;
        this.type = Structure.binary;
        atomicSentence = null;
        operator = opr;
        this.subsentence = new ArrayList<Sentence>();
        this.subsentence.add(sentence1);
        this.subsentence.add(sentence2);
        check = false;
    }

    public Pair<Operation, Integer> returnOperation() {
        switch (this.type) {
            case atomic:
                return null;
            case unary:
                Operation operation = PropOperation.valueOf(this.operator + subsentence.get(0).operator);
                return new Pair(operation, 1);
            case binary:
                return new Pair(Structure.valueOf(operator), 2);
        }
    }

    public String returnSentence() {
        switch (this.type) {
            case atomic:
                return atomicSentence;
            case unary:
                return operator + subsentence.get(0);
            case binary:
                return  subsentence.get(0) + operator + subsentence.get(1);
        }
    }

}
