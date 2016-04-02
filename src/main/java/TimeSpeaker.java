import java.util.Calendar;

/**
 * This class converts any time into word form
 * e.g  05:15 -> quarter past five
 *      03:30 -> half past three
 *      11:40 -> twenty minutes to twelve
 */
public class TimeSpeaker {

    private static String digits[] = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};
    private static String units[] = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty"};

    public static void speak() {
        System.out.println("TimeSpeaker Started...");

        // Getting Current time and converting into word form
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR);
        int m = calendar.get(Calendar.MINUTE);
        String str = null;
        if (m == 0) {
            str = inWord(h) + " o' clock";
        } else if (m == 15) {
            str = "quarter past " + inWord(h);
        } else if (m == 45) {
            str = "quarter to " + inWord((h + 1) % 12);
        } else if (m == 30) {
            str = "half past " + inWord(h);
        } else if (m > 0 && m < 30) {
            str = inWord(m) + ((m == 1) ? " minute " : " minutes ") + " past " + inWord(h);
        } else if (m > 30 && m < 60) {
            str = inWord(60 - m) + " minutes " + " to " + inWord((h + 1) % 12);
        }

        // speaks out current time repeatedly 10 times
        System.out.println("AlarmAgent Started...");
        for (int i = 0; i < 10; i++) {
            TextToSpeech.speak("It is " + str + " ...now");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static String inWord(int n) {
        if (n <= 20) {
            return digits[n];
        }
        return units[n / 10] + " " + digits[n % 10];
    }

}
