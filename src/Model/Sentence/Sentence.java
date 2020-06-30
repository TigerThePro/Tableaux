package Model.Sentence;

import Model.Operation.Operation;
import Model.Operation.PropOperation;
import Model.Structure;

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
    public PropOperation operation;


    // To create atomic sentence eg: p
    public Sentence(String input) {
//        this.atomic = true;
        this.type = Structure.atomic;
        atomicSentence = input;
        subsentence = null;
        operator = null;
        check = true;
        operation = PropOperation.none;
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
        operation = this.getOperation();
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
        operation = this.getOperation();
    }


    // Return the operation to do fro this sentence
    public PropOperation returnOperation() {
        return operation;
    }

    // Return priority of this sentence
    // priority is the number of extension the operation takes
    public int returnPriority() {
        if (type == Structure.atomic ||
                (type == Structure.unary && subsentence.get(0).type == Structure.atomic)) {
            return 0;
        } else {
            switch (this.operation) {
                case implies:
                case or:
                case iff:
                case notand:
                case notiff:
                    return 2;
                default:
                    return 1;
            }
        }
    }

    public void check() {
        this.check = true;
    }


    // Return the sentence in string
    public String returnSentence() {
        switch (this.type) {
            case unary:
                return operator + " " + getString(subsentence.get(0));
            case binary:
                String sen1 = getString(subsentence.get(0));
                String sen2 = getString(subsentence.get(1));
                return sen1 + " " + operator + " " + sen2;
            default:
                return atomicSentence;
        }
    }

    // Helper
    // to add parenthesis
    private String getString(Sentence sentence) {
        if (sentence.type == Structure.atomic) {
            return sentence.atomicSentence;
        } else {
            return "(" + sentence.returnSentence() + ")";
        }
    }

    // Helper
    // get the operation
    private PropOperation getOperation() {
        switch (this.type) {
            case unary:
                if (subsentence.get(0).type != Structure.atomic) {
                    return PropOperation.valueOf(this.operator + subsentence.get(0).operator);
                }
                return PropOperation.none;
            case binary:
                return PropOperation.valueOf(this.operator);
            default:
                return PropOperation.none;
        }
    }

}
