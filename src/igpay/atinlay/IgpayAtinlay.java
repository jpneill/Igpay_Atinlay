/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package igpay.atinlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class IgpayAtinlay extends JFrame{
    
    private JTextArea inputArea;
    private JTextArea outputText;
    
    public IgpayAtinlay(){
        Init_UI();
    }

    public final void Init_UI(){
        
        JPanel backPanel = new JPanel();
        backPanel.setLayout(null);
        
        //enabling scrolling in the text areas
        JScrollPane leftPane = new JScrollPane();
        JScrollPane rightPane = new JScrollPane();
        
        //create a text input area
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        
        leftPane.getViewport().add(inputArea);
        leftPane.setBounds(10, 10, 320, 345);
        backPanel.add(leftPane);
        
        //create text output
        outputText = new JTextArea();
        outputText.setLineWrap(true);
        outputText.setWrapStyleWord(true);
        outputText.setEditable(false);
        
        rightPane.getViewport().add(outputText);
        rightPane.setBounds(472, 10, 300, 345);
        backPanel.add(rightPane);
        
        //create a translate button
        JButton translateButton = new JButton("Translate");
        translateButton.setBounds(340, 170, 120, 30);
        translateButton.addActionListener(new ActionListener(){            
            public void actionPerformed(ActionEvent ae) {
                String input = inputArea.getText();
                outputText.setText(Translate(input));
            }            
        });
        
        backPanel.add(translateButton);
        
        getContentPane().add(backPanel);
        setTitle("Igpay Atinlay");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public String Translate(String string){
        //create a 1D array of all the words in the input string
        String[] inp = string.split(" ");        
        boolean vowelStart;
        int consonantCount;
        char temp;
        String output = "";
        
        //take each word and translate it into pig latin
        for(int i = 0; i < inp.length; i++){
            //divide the word into an array of chars and assume starts with con
            char[] letters = inp[i].toCharArray();
            vowelStart = false;
            consonantCount = 0;
            
            //starting at the beginning of the array identify how many
            //consonants are at the beginning of the word
            for(int j = 0; j < letters.length; j++){                
                if(j == 0 && (letters[j] == 'a' || letters[j] == 'e' || 
                        letters[j] == 'i' || letters[j] == 'o' || 
                        letters[j] == 'u')){
                    
                    vowelStart = true;
                    break;
                    
                } else if(letters[j] == 'a' || letters[j] == 'e' || 
                        letters[j] == 'i' || letters[j] == 'o' || 
                        letters[j] == 'u')
                    break;
                
                else
                    consonantCount++;                
            }
            //if vowel put the vowel at the end of the word
            if(vowelStart){
                temp = letters[0];
                for(int j = 0; j < letters.length - 1; j++){
                    letters[j] = letters[j+1];
                }
                letters[letters.length - 1] = temp;
            } else {
                //put the consonants at the end of the word
                for(int k = 0; k < consonantCount; k++) {
                        temp = letters[0];
                    for(int j = 0; j < letters.length - 1; j++){
                        letters[j] = letters[j+1];
                    }
                    letters[letters.length - 1] = temp;
                }
            }
            //reform the new word
            inp[i] = "";
            for(int j = 0; j < letters.length; j++){
                inp[i] += letters[j];
            }
            
            //add "ay" onto the end of the word
            inp[i] += "ay ";
            output += inp[i];
        }
        
        //create a new string to be output to the label as translation
        
        return output;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                IgpayAtinlay pigLatin = new IgpayAtinlay();
                pigLatin.setVisible(true);
            }
            
        });
    }
}
