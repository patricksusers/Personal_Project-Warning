
package warning;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Warning {

    public static void main(String[] args) throws InterruptedException, IOException { 
        
        SimpleDateFormat SDF = new SimpleDateFormat(); 
        SDF.applyPattern("HH");
        Date date = new Date();
        
        //Configurations
        int firstWarningHour = 21; 
        int secondWarning = 15;
        int timeBeforeShutdown = 5;
        
        //Shutdown
        String commands = "shutdown";
        
        //Passwords
        String passwords = "Administrations";
        
        Boolean firstAlreadyWarned = false;
        Boolean secondAlreadyWarned = false;
              
                
        do {
                        
            int currentHours = date.getHours();
            
            TimeUnit.SECONDS.sleep(1);
                  
            if (currentHours == firstWarningHour && firstAlreadyWarned == false) {
                                
                playFirstWarning();                
                debugAndUpdateDate("1");
                
                ImageIcon icon = new ImageIcon("src/images/firstWarningIcon.png");  
                JOptionPane.showMessageDialog(null, "Your computer will be turn off in " + timeBeforeShutdown + " Minutes!", 
                            "Teddy Reminder", JOptionPane.YES_NO_OPTION, icon);
                
                TimeUnit.SECONDS.sleep(1);
                
                firstAlreadyWarned = true;
                    
            } else if (currentHours == secondWarning && secondAlreadyWarned == false) {
                                
                playSecondWarning();
                debugAndUpdateDate("2");

                ImageIcon icon = new ImageIcon("src/images/secondWarningIcon.png");
                JOptionPane.showMessageDialog(null, "Your computer will be turn off in " + timeBeforeShutdown + " Minutes!", 
                            "Teddy Reminder", JOptionPane.YES_NO_OPTION, icon);
                
                TimeUnit.SECONDS.sleep(timeBeforeShutdown);
                
                secondAlreadyWarned = true;
                    
                    
                } else if (currentHours == secondWarning && secondAlreadyWarned == true) {
                    
                    debugAndUpdateDate("3");
                    
                    JFrame frame = new JFrame();
                    frame.setLocationRelativeTo(null);
                    frame.setSize(350,300);
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(1, 0, 5, 5));
                    JPanel finalLargePanel = new JPanel();
                    
                    JLabel lblFinal = new JLabel("Your computer will be turn off in 5 Minutes!");
                    JButton btnOK = new JButton("Turn off now");
                    btnOK.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                frame.setVisible(false);
                                playShutdown();
                                Runtime rt = Runtime.getRuntime();
                                rt.exec(new String[]{"cmd.exe",commands});
                                TimeUnit.MINUTES.sleep(5);
                            } catch (InterruptedException ex) {
                                System.out.println("[Error] - " + ex);
                            } catch (IOException ex) {
                                System.out.println("[Error] - " + ex);
                            }
                        }
                    });
                    
                    JButton btnPassword = new JButton("I have password");
                    btnPassword.addActionListener(new ActionListener() {
                        @Override
                        
                        public void actionPerformed(ActionEvent ae) {
                            frame.setVisible(false);JFrame passwordFrame = new JFrame();
                            JPanel passwordPanel = new JPanel();
                            JLabel lblPassword = new JLabel("Please type the password to stop for 60 seconds.");
                            String thePassword = passwords;
                            JLabel lblPasswordStatus = new JLabel("");
                            JTextField tfPassword = new JTextField();
                            JButton btnSubmit = new JButton("Submit");
                            btnSubmit.disable();
                            btnSubmit.addActionListener(new ActionListener() {
                                
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    if (tfPassword.getText() == thePassword) {
                                        passwordFrame.setVisible(false);
                                        try {
                                            TimeUnit.MINUTES.sleep(60);
                                        } catch (InterruptedException ex) {
                                        }
                                    } else {
                                        lblPasswordStatus.setText("Incorrect password");
                                    }
                                }
                            });
                            
                            passwordFrame.setSize(350,300);
                            passwordPanel.setLayout(new GridLayout(4,0,15,15));
                            passwordPanel.add(lblPassword);
                            passwordPanel.add(tfPassword);
                            passwordPanel.add(btnSubmit);
                            passwordPanel.add(lblPasswordStatus);
                            passwordFrame.add(passwordPanel);
                            passwordFrame.setLocationRelativeTo(null);
                            passwordFrame.setVisible(true);
                        }
                    });
                    
                    finalLargePanel.setLayout(new GridLayout(3,1,5,5));
                    finalLargePanel.add(lblFinal);
                    finalLargePanel.add(btnOK);
                    finalLargePanel.add(btnPassword);
                    
                    panel.add(finalLargePanel);
                    frame.add(panel);
                    
                    frame.setVisible(true);
                    
                    TimeUnit.MINUTES.sleep(5);
                    
                    try {
                        frame.setVisible(false);
                        playShutdown();
                        Runtime rt = Runtime.getRuntime();
                        rt.exec(new String[]{"cmd.exe",commands});
                        TimeUnit.MINUTES.sleep(5);
                    } catch (InterruptedException ex) {
                        System.out.println("[Error] - " + ex);
                }catch (IOException ex) {
                    System.out.println("[Error] - " + ex);
                }
                }
            
        
        } while (true);
    
    }
    
    public static void debugAndUpdateDate(String debugNumber) {
        
        SimpleDateFormat SDF = new SimpleDateFormat(); 
        SDF.applyPattern("HH");
        Date date = new Date();
        
        int currentHours = date.getHours();
        int currentMinutes = date.getMinutes();
        int currentSeconds = date.getSeconds();
        
    }
    
    public static void playFirstWarning() {
        
        try {
            
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/firstWarning.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        
        } catch(Exception ex) {
            System.out.println("[Error] - " + ex);
    }
        
  }
    
    public static void playSecondWarning() {
        
        try {
            
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/secondWarning.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        
        } catch(Exception ex) {
            System.out.println("[Error] - " + ex);
    }
        
  }
    
    public static void playShutdown() {
        
        try {
            
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/shutdown.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        
        } catch(Exception ex) {
            System.out.println("[Error] - " + ex);
    }
        
  }
    
}