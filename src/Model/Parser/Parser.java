package Model.Parser;

import Model.Sentence.Sentence;

import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public Parser() {
    }


    public Sentence parseSentence(String input) throws Exception {
        String string = input.trim();
        string = string.toLowerCase();
        if (string.length() < 1) {
            throw new Exception("Empty input");
        }
        try {
            return iterateInput2(string, null);
        } catch (Exception e) {
            throw e;
        }
    }


//    private Sentence iterateInput(String input, Sentence pSentence) throws Exception{
//        try {
//            input = input.trim();
//            if (input.length() == 1 && input.matches("[a-z]")) {
//                return new Sentence(input);
//            } else if (input.length() > 2 && pSentence == null) {
//
//                if (input.substring(0, 1).equals("(")) {
//                    int end = findEnd(input);
//                    return iterateInput(input.substring(end + 2),
//                            iterateInput(input.substring(1, end), null));
//                } else if (input.substring(0, 2).equals("or") && pSentence != null)  {
//                    //
//                }
//
//            } else if(pSentence != null && input.matches(" +")) {
//                return pSentence;
//            }
//            return null;
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }


    // Got frustrated, so decided to hardcode
    private Sentence iterateInput2(String input, Sentence pSentence) throws Exception{
        try {
            input = input.trim();
            if (pSentence == null) {
                // ATOMIC
                if (input.matches("[a-z]")) {
                    return new Sentence(input);
                    // (
                } else if (input.substring(0,1).equals("(")) {

                    int end = findEnd(input);

                    return iterateInput2(input.substring(end + 1),
                            iterateInput2(input.substring(1, end), null));
                    // NOT
                } else if (input.substring(0,3).equals("not")) {

                    return new Sentence("not",
                            iterateInput2(input.substring(3), null));
                    // ATOMIC
                } else if (input.substring(0, 1).matches("[a-z]") && input.substring(1, 2).equals(" ")) {
                    return iterateInput2(input.substring(2), new Sentence(input.substring(0,1)));
                }
            } else {
                // AND
                if (input.length() == 0) {
                    return pSentence;
                }
                if (input.substring(0, 3).equals("and")) {
                    return new Sentence(pSentence, "and",
                            iterateInput2(input.substring(3), null));
                    // OR
                } else if(input.substring(0, 2).equals("or")) {
                    return new Sentence(pSentence, "or", iterateInput2(input.substring(2), null));
                    // IFF
                } else if (input.substring(0, 3).equals("iff")) {
                    return new Sentence(pSentence, "iff", iterateInput2(input.substring(3), null));
                    //IMPLIES
                } else if (input.substring(0, 7).equals("implies")) {
                    return new Sentence(pSentence, "implies", iterateInput2(input.substring(7), null));
                    // NONE
                }
            }
            throw new Exception();
        } catch (Exception e) {
            throw e;
        }
    }


    // Helper finds the end of a parentheses
    private int findEnd(String input) throws Exception{
        for (int i = 1; i < input.length(); i++) {
            String s = input.substring(i, i+1);
            if (s.equals(")")) {
                return i;
            } else if (s.equals("(")) {
                 i += findEnd(input.substring(i + 1)) + 1;
            }
        }
        throw new Exception();
    }




}



    // Having trouble rouble implementing using regex
    // problems with escape character
    // so going to implement from scratch to iterate over input string

//    public Sentence parseSentence(String input) throws Exception {
//        String string = input.toLowerCase();
//        String[] substrings = string.split(" ");
//
//        if (string.matches("[a-z]")) {
//            return new Sentence(string);
//        } else if (string.matches("not +[a-z]")) {
//            substrings = string.split(" ");
//            return new Sentence("not", parseSentence(substrings[1]));
//        } else if (string.matches("[a-z] +(or|and|implies|iff) +[a-z]")) {
//            substrings = string.split(" ");
//            return new Sentence(
//                    parseSentence(substrings[0]),
//                    substrings[1],
//                    parseSentence(substrings[2])
//            );
//        } else if (string.matches("not + \\(.+\\)")){
//            Pattern pattern = Pattern.compile("(.+)");
//            Matcher matcher = pattern.matcher(string);
//            return new Sentence("not", parseSentence(matcher.group(1)));
//        }
////        else if (string.matches("/(.+/) +(or|and|implies|iff) +/(.+/)")) {
////            return new Senten
////        }
//        throw new Exception("Not valid");
//    }
//}
