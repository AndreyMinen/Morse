/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morsetest2;

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
public class MorseTest2 {

    /**
     * @param args the command line arguments
     */
    
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

    
    public static void main(String[] args) throws LineUnavailableException, InterruptedException {
        final AudioFormat af = new AudioFormat(16000, 8, 1, true, true);
       SourceDataLine line = AudioSystem.getSourceDataLine(af);
       line.open(af);
       line.start();
       byte [] toneBuffer = createSinWaveBuffer(800, 50);
       byte [] toneBuffer1 = createSinWaveBuffer(800, 50);
       byte [] toneBuffer2 = createSinWaveBuffer(800, 50);
       byte [] toneBuffer3 = createSinWaveBuffer(800, 50);
       line.write(toneBuffer, 0, toneBuffer.length);
       Thread.sleep(150);
       line.write(toneBuffer1, 0, toneBuffer1.length);
       Thread.sleep(150);
       line.write(toneBuffer2, 0, toneBuffer2.length);
       Thread.sleep(150);
       line.write(toneBuffer3, 0, toneBuffer3.length);
       line.drain();
       line.close();
    }
    
}
