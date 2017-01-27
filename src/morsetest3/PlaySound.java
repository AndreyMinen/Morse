/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morsetest3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author ADMIN
 */
public class PlaySound {
    
    private ArrayList<String> chars;

    public PlaySound(ArrayList<String> chars) {
        this.chars = chars;
        try {
            play();
        } catch (InterruptedException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected static final int SAMPLE_RATE = 16 * 1024;


   public static byte[] createSinWaveBuffer(double freq, int ms) {
       int samples = (int)((ms * SAMPLE_RATE) / 1000);
       byte[] output = new byte[samples];
           //
       double period = (double)SAMPLE_RATE / freq;
       for (int i = 0; i < output.length; i++) {
           double angle = 2.0 * Math.PI * i / period;
           output[i] = (byte)(Math.sin(angle) * 127f);  }

       return output;
   }
   
   private void createWave(int duration){
        try {
            final AudioFormat af = new AudioFormat(16000, 8, 1, true, true);
            SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open(af);
            line.start();
            byte [] toneBuffer = createSinWaveBuffer(800, duration);
            line.write(toneBuffer, 0, toneBuffer.length);
            line.drain();
            line.close();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
   
   private void play() throws InterruptedException{
       String[] let;
       for(int i=0;i<chars.size();i++){
           let=chars.get(i).split("");
           for(int j=0;j<let.length;j++){
               if(let[j].equals(".")){
               createWave(70);
                }else if(let[j].equals("-")){
               createWave(210);
                }
           }
           Thread.sleep(100);
       }
       
   }
   
}
