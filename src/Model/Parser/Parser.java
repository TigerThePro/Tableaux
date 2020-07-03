package Model.Parser;

import Model.Sentence.Sentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public Parser() {
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
//        } else if (string.matches("not + /(.+/)")){
//            Pattern pattern = Pattern.compile("(.+)");
//            Matcher matcher = pattern.matcher(string);
//            return new Sentence("not", parseSentence(matcher.group(1)));
//        }
////        else if (string.matches("/(.+/) +(or|and|implies|iff) +/(.+/)")) {
////            return new Senten
////        }
//        throw new Exception("Not valid");
//    }
}
