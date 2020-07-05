import Model.*;
import Model.Parser.Parser;
import Model.Sentence.Sentence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;


public class UI extends JFrame implements ActionListener {
    JLabel instruciton;
    JButton button;
    JTextArea premise;
    JTextField conclusion;
    JLabel result;
    String rs = "<html> Result: ";
    Parser parser;
    Tableaux table;

    public UI() {
        parser = new Parser();
        table = new Tableaux();
        setLayout(new FlowLayout());

//        instruciton = new JLabel("Example: p \t not q \t p and q \t p implies q \t p iff q \n" +
//                "(not (p implies q) or (not (not s iff t))");
        instruciton = new JLabel("<html><center><p>Examples: <br>" +
                "p <br> not p <br> p and q <br> p implies q <br> p iff q <br>" +
                "not (not p)) <br> p and (not t) <br> (u and v) implies u <br> <hr>" +
                "(not (p implies q) or (not (not s iff t))" +
                "</p></center></html>");
        premise = new JTextArea("enter premises here", 15, 40);
        conclusion = new JTextField("enter conclustion here", 30);
        button = new JButton("Make Tableaux");
        button.addActionListener(this);
        result = new JLabel(rs + "</html>");

        add(instruciton);
        add(premise);
        add(conclusion);
        add(button);
        add(result);
    }

    public static void main(String[] args) {

        UI ui = new UI();
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setSize(600, 600);
        ui.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String premiseInput = premise.getText();
        String concluInput = conclusion.getText();
        try {
            Branch root = takeInput(premiseInput, concluInput);
            if (table.extend(root)) {
//                String res = displayBranch(root);
//                result.setText(rs + "Close" +
//                        "<br><hr>" + displayBranch(root) + "</html>");
                result.setText(rs + "Close" +
                        "<br><hr>" + root.returnBranch2(0) + "</html>");
            } else {
                //TODO
//                result.setText(rs + "Open" +
//                        "<br><hr>" + displayBranch(root) + "</html");
                result.setText(rs + "Open" +
                        "<br><hr>" + root.returnBranch2(0) + "</html");
            }
        } catch (Exception x) {
            result.setText(rs + "INVALID INPUT !!! </html>");
        }
    }

    private Branch takeInput(String pInput, String cInput) throws Exception{
        String[] strings = pInput.split("\n");
        ArrayList<Sentence> premise = new ArrayList<>();
        try {
            for (int i = 0; i < strings.length; i++) {
                premise.add(parser.parseSentence(strings[i]));
            }
            return table.prepareTableaux(premise, parser.parseSentence(cInput));
        } catch (Exception x) {
            throw x;
        }
    }

//    private String displayBranch(Branch root) {
//        String res = "";
//        int lastLevel = 0;
//        ArrayList<Pair> branches = new ArrayList<>();
//        Pair pair = new Pair(root, 1);
//        branches.add(pair);
//        ListIterator<Pair> todo = branches.listIterator();
//
//        while(todo.hasNext()) {
//            for (int i = 0; i < root.sentenceList.size(); i++) {
//                res = res + "<br>" + root.sentenceList.get(i).returnSentence();
//            }
//            if (root.left != null && root.right != null) {
////                todo.add(root.left);
////                todo.add(root.right);
//            }
//        }
//        return res;
//    }

}
