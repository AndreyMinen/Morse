/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morsetest;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class MorseTest {

    /**
     * @param args the command line arguments
     */
    
    	public static byte[] makeSinWave (AudioFormat audioFormat, double frequency, long duration, TimeUnit timeUnit)
	{
		byte[] buffer = new byte[(int) (timeUnit.toSeconds(duration) * audioFormat.getSampleRate())];
		double period = audioFormat.getSampleRate() / frequency;
		for (int i = 0; i < buffer.length; i++) {
			double angle = 2d * Math.PI * i / period;
			buffer[i] = (byte) (Math.sin(angle) * 127d);
		}
		return buffer;
	}
    
    public static void main(String[] args) {
                try {
                    final AudioFormat audioFormat = new AudioFormat(44100, 8, 1, true, true);
                    SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
                    line.open(audioFormat);
                    line.start();
                    byte[] buffer = makeSinWave(audioFormat, 800, 1, TimeUnit.SECONDS);
                    line.write(buffer, 0, buffer.length);
                    line.drain();
                    line.close();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(MorseTest.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
