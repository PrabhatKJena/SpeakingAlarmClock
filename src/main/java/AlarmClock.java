import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class creates the alarm clock using Java Swing API, having feature to set alarm for a specific time
 * and triggers the TimeSpeaker to speak the time on the time for which alarm is set
 */
public class AlarmClock {

    public static void main(String[] args) {
        // Creating frame
        JFrame frame = new JFrame("Alarm by PK");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        Container contentPane = frame.getContentPane();
        // Adding timer label
        final JLabel jLabel = new JLabel("00:00:00", JLabel.CENTER);
        jLabel.setFont(new Font("Arial", 1, 18));
        jLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jLabel.setForeground(Color.BLUE);
        contentPane.add(jLabel);
        contentPane.setLayout(new FlowLayout());

        // Adding HH dropdown
        final JComboBox<String> hhBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++)
            hhBox.addItem(String.format("%02d", i));
        contentPane.add(hhBox);

        // Adding MM dropdown
        final JComboBox<String> mmBox = new JComboBox<>();
        for (int i = 0; i <= 60; i += 1)
            mmBox.addItem(String.format("%02d", i));
        contentPane.add(mmBox);

        // Adding AM/PM dropdown
        final JComboBox<String> amBox = new JComboBox<>();
        amBox.addItem("AM");
        amBox.addItem("PM");
        contentPane.add(amBox);

        // Adding Set Alarm Button
        JButton jButton = new JButton("Set Alarm");
        contentPane.add(jButton);
        // Adding onClick action listener
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hh = (String) hhBox.getSelectedItem();
                String mm = (String) mmBox.getSelectedItem();
                String am = (String) amBox.getSelectedItem();
                final String alarmTime = hh + ":" + mm + ":00 " + am;
                System.out.println("Alarm Set for : "+alarmTime);

                // Checker thread checks the set alarm time with current time
                Thread alarmChecker = new Thread() {
                    @Override
                    public void run() {
                        System.out.println("Checker Started...."+jLabel.getText());
                        while (true) {
                            if(alarmTime.equalsIgnoreCase(jLabel.getText())){
                                TimeSpeaker.speak();
                            }
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e1) {
                            }
                        }
                    }
                };
                alarmChecker.start();
            }
        });

        // Timer thread update the current time on screen
        Thread timer = new Thread() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void run() {
                while (true) {
                    try {
                        String time = this.format.format(new Date());
                        jLabel.setText(time);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        timer.start();
    }
}
