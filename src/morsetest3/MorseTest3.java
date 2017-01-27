/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morsetest3;

import java.util.ArrayList;


/**
 *
 * @author ADMIN
 */
public class MorseTest3 {

    
    public static void main(String[] args) {
        ArrayList<String> array=new ArrayList<String>();
        array.add(".-");
        array.add("-...");
        array.add("...-");
        PlaySound  play=new PlaySound(array);
    }
    
}
