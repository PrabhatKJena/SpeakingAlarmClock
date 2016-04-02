import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * This is the class responsible of converting text to speech using freetts (Free Text To Speech) library
 *
 * Down load library from http://www.java2s.com/Code/Jar/f/Downloadfreetts122jar.htm
 * OR
 * add lib/freetts.jar from "lib" folder in "freetts-1.2.2-src.zip"
 *                         (downloadable from https://sourceforge.net/projects/freetts/files/FreeTTS/FreeTTS%201.2.2/)
 */
public class TextToSpeech {
    private static final String voiceName = "kevin16"; // this is one of the voice name available in default
    private static void doSpeak(String text) {
        try {
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice(voiceName);

           /* Voice voices1[] = voiceManager.getVoices();  // Using this you can get all available voice names
            System.out.println("Available Voices");
            for (int i = 0; i < voices1.length; i++)
                System.out.println(voices1[i].getName());*/

            if (voice != null) {
                voice.allocate();
                // Set 120 words per minute
                voice.setRate(120);
                voice.speak(text);
                /*FileInputStream fileInputStream = new FileInputStream("d:/ttsInput.txt");
                voice.speak(fileInputStream);*/

                voice.deallocate();
            } else {
                System.out.println("No Voice Available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void speak(String text){
        doSpeak(text);
    }
}
