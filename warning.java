/*
Patrick Looi
0121712@kdu-online.com
 */

package javafinalexam2;

/* 
Comment and Remark - 
Import are automatically added based on ALT+ENTER suggestions.
The unsed import is removed at the end 
*/

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JavaFinalExam2 extends JFrame implements ActionListener{
    
    /*
    Comment and Remark - 
    I have decide to declare and initialize every viarable at the start along 
    with appropriate naming for ease of finding the viarable.
    */
    
    //JFrame Declaration
    JFrame frameMenu = new JFrame("Main Menu");
    JFrame frameThread = new JFrame("Thread");
    JFrame frameBIO = new JFrame("Buffered IO");
    JFrame frameStack = new JFrame("Stack");

    //Main Menu Declaration
    private JLabel lblMainChoose = new JLabel("Please choose your option:");
    private JButton btnMainThread = new JButton("Thread");
    private JButton btnMainBufferedIO = new JButton("Buffered IO");
    private JButton btnMainStack = new JButton("Stack");  
    private JLabel lblMainEmpty = new JLabel();

    //Thread Declaration
    private final runnables runnable1 = new runnables();
    private final runnables runnable2 = new runnables();
    private int intInput1, intInput2;
    private int currentCalculation = 0;
    private int storedCalculation = 0;
    
    private JLabel lblThreadSN = new JLabel("Start No:");
    private JTextField txtThreadSN = new JTextField();
    private JLabel lblThreadEO = new JLabel("End No:");
    private JTextField txtThreadEN = new JTextField();;
    private JLabel lblThreadThread = new JLabel("Thread");
    private JTextField txtThreadThread = new JTextField();;
    private JButton btnThreadStart = new JButton("Start");
    private JButton btnThreadMenu = new JButton("Menu");
    
    //Buffered IO Declaration
    private String stringContent = "";
    
    private JLabel lblBIOaddItem = new JLabel ("Add item:");
    private JTextField txtBIOaddItem = new JTextField();
    private JLabel lblBIOClickBtn = new JLabel ("Click button to add:");
    private JButton btnBIOAdd = new JButton("Add");
    private JLabel lblBIOContent = new JLabel("Content:");
    private JTextField txtBIOContent = new JTextField();
    private JButton btnBIOSave = new JButton("Save");
    private JButton btnBIOMenu = new JButton("Menu");
    
    //Stack Declaration
    private Stack<String> stack = new Stack<String>();

    private JLabel lblStackPush = new JLabel("Push Item:");
    private JTextField txtStackPush = new JTextField();
    private JLabel lblStackClickButton = new JLabel("Click button to push:");
    private JButton btnStackPush = new JButton("Push");
    private JLabel lblStackStack = new JLabel("Stack Element:");
    private JTextField txtStackStack = new JTextField();
    private JButton btnStackPop = new JButton("Pop");
    private JButton btnStackMenu = new JButton("Menu");
    
    public JavaFinalExam2() {
        
        //Main Menu Frame 
        frameMenu.setVisible(true);
        frameMenu.setSize(500,500);
        
        /*
        Comment and Remark - 
        I have created a JPanel to hold all the component, since i have already
        declared everything above, add i had to do is just add it to JPanel.
        */
        
        JPanel pnlMenu = new JPanel();
        
        /*
        Comment and Remark - 
        GridLayout is used in every JPanel.
        */
        
        pnlMenu.setLayout(new GridLayout(5,0));
        pnlMenu.add(lblMainChoose);
        pnlMenu.add(btnMainThread);
        
        /*
        Comment and Remark - 
        I have also added .addActionListener which can be tracked with ActionPerformed
        to every single button.
        */
        
        btnMainThread.addActionListener(this);
        pnlMenu.add(btnMainBufferedIO);
        btnMainBufferedIO.addActionListener(this);
        pnlMenu.add(btnMainStack);
        btnMainStack.addActionListener(this);
        pnlMenu.add(lblMainEmpty);
        
        frameMenu.add(pnlMenu);

        //Thread Frame 
        frameThread.setVisible(false);
        frameThread.setSize(500,500);
        
        JPanel pnlThread = new JPanel();
        pnlThread.setLayout(new GridLayout(4,2));
        pnlThread.add(lblThreadSN);
        pnlThread.add(txtThreadSN);
        pnlThread.add(lblThreadEO);
        pnlThread.add(txtThreadEN);
        pnlThread.add(lblThreadThread);
        pnlThread.add(txtThreadThread);
        pnlThread.add(btnThreadStart);
        btnThreadStart.addActionListener(this);
        pnlThread.add(btnThreadMenu);
        btnThreadMenu.addActionListener(this);
        
        frameThread.add(pnlThread);   
        
        //BufferedIO Frame 
        frameBIO.setVisible(false);
        frameBIO.setSize(500,500);
        
        JPanel pnlBIO = new JPanel();
        pnlBIO.setLayout(new GridLayout(4,2));
        pnlBIO.add(lblBIOaddItem);
        pnlBIO.add(txtBIOaddItem);
        pnlBIO.add(lblBIOClickBtn);
        pnlBIO.add(btnBIOAdd);
        btnBIOAdd.addActionListener(this);
        pnlBIO.add(lblBIOContent);
        pnlBIO.add(txtBIOContent);
        pnlBIO.add(btnBIOSave);
        btnBIOSave.addActionListener(this);
        pnlBIO.add(btnBIOMenu);
        btnBIOMenu.addActionListener(this);
        
        frameBIO.add(pnlBIO);
        
        //Stack Frame 
        frameStack.setVisible(false);
        frameStack.setSize(500,500);
        
        JPanel pnlStack = new JPanel();
        pnlStack.setLayout(new GridLayout(4,2));
        pnlStack.add(lblStackPush);
        pnlStack.add(txtStackPush);
        pnlStack.add(lblStackClickButton);
        pnlStack.add(btnStackPush);
        btnStackPush.addActionListener(this);
        pnlStack.add(lblStackStack);
        pnlStack.add(txtStackStack);
        pnlStack.add(btnStackPop);
        btnStackPop.addActionListener(this);
        pnlStack.add(btnStackMenu);
        btnStackMenu.addActionListener(this);
        
        frameStack.add(pnlStack);     
    
    }

    public static void main(String[] args) {
        
        /*
        Comment and Remark - 
        Here's the main, when the program start, it will create a default constructor.
        */
        
        JavaFinalExam2 start = new JavaFinalExam2();

    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        
        /*
        Comment and Remark - 
        When the program is closing, it will dispose everything and exit the program.
        */
        dispose();
        System.exit(0);
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        /*
        Comment and Remark - 
        Here is a simple if else statement, when a button with .addActionListener(this) is clicked.
        they will search for the button that clicked, then run their respective code.
        */
        
        if (ae.getSource() == btnMainThread) {
            
            /*
            Comment and Remark - 
            Here, i use a simply setVisible to open and close a menu for all JFrame.
            */
            
            frameMenu.setVisible(false);
            frameThread.setVisible(true);
            
        } else if (ae.getSource() == btnMainBufferedIO) {

            frameMenu.setVisible(false);
            frameBIO.setVisible(true);
            
        } else if (ae.getSource() == btnMainStack) {
            
            frameMenu.setVisible(false);
            frameStack.setVisible(true);
            
        } else if (ae.getSource() == btnThreadStart) {
            
            intInput1 = Integer.parseInt(txtThreadSN.getText());
            intInput2 = Integer.parseInt(txtThreadEN.getText());
            
            try {
                
                /*
                Comment and Remark - 
                Here is the two thread that is the requirement.
                */
            
            Thread firstThread = new Thread(runnable1);
            firstThread.start();

            Thread secondThread = new Thread(runnable2);
            secondThread.start();
            
            } catch (Exception ex) {
                
                /*
                Comment and Remark - 
                If something went wrong, a warning message along with reason will
                be displayed here.
                */
                
                System.out.println("[WARN] - Unexpected Error - " + ex);
                
            }
            
        } else if (ae.getSource() == btnThreadMenu) {
            
            frameMenu.setVisible(true);
            frameThread.setVisible(false);

        } else if (ae.getSource() == btnBIOAdd) {
            
            /*
            Comment and Remark - 
            This line of code will collect user input from JTextField, then add a space behind it.
            */
            
            String stringBIOaddItem = txtBIOaddItem.getText() + " ";     
            
            /*
            Comment and Remark - 
            Once it is done, all the item will be added into stringContent, which will be displayed on 
            txtBufferedIO JTextField.
            */
            stringContent = stringContent + stringBIOaddItem;
            
            txtBIOContent.setText(stringContent);
            
        } else if (ae.getSource() == btnBIOSave) {
            
            /*
            Comment and Remark - 
            This line of code will collect all the existing information from JTextField
            and store it into a String viarable.
            */

            String stringBIOContent = txtBIOContent.getText();
            
            /*
            Comment and Remark - 
            Since you can only write in byte, I had to convert String to Byte.
            */
            byte stringBIOContentToByte[] = stringBIOContent.getBytes();
            
            try {
                
                /*
                Comment and Remark - 
                Create a texts.txt file.
                */
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("texts.txt"));
                
                /*
                Comment and Remark - 
                Write the file.
                */
                
                bos.write(stringBIOContentToByte);
                
                /*
                Comment and Remark - 
                Close the file
                */
                
                bos.close();
                
                System.out.println("[SUCCESS] - Successfully save texts to texts.txt");
                
            } catch (IOException ex) {
                
                System.out.println("[WARN] - Unexpected Error - " + ex);
                
            }
            
        } else if (ae.getSource() == btnBIOMenu) {
            
            frameMenu.setVisible(true);
            frameBIO.setVisible(false);
            
        } else if (ae.getSource() == btnStackPush) {
            
            /*
            Comment and Remark - 
            This part of code will collect input from JTextField and save it to 
            String viarable.
            */
            
            String getStack = txtStackPush.getText();
            
            /*
            Comment and Remark - 
            It will "save" the information to stack using .push.
            */
            
            stack.push(getStack);
            
            /*
            Comment and Remark - 
            Convert stack output and save it to String viarable.
            */
            
            String stringStack = stack.toString();
            
            /*
            Comment and Remark - 
            This way, i could display the stack in JTextField without receiving
            any error.
            */
            
            txtStackStack.setText(stringStack);
            
            System.out.println(stack);
            
        } else if (ae.getSource() == btnStackPop) {
            
            /*
            Comment and Remark - 
            This is a very important part of code, this if else statement will
            check if a stack is empty or not, if it is empty, nothing will be 
            remove from the stack (also known as pop), that way, we could avoid 
            an error in the code.
            */
            
            if (stack.isEmpty()) {
                
                System.out.println("[WARN] - The stack is empty, nothing has changed to prevent error.");
                System.out.println(stack);
                
            } else {
                
                /*
                Comment and Remark - 
                However, if the stack is not empty, it will start removing item
                from the stack using .pop.
                */
                
                stack.pop();
                String stringStack = stack.toString();
                txtStackStack.setText(stringStack);
                
                System.out.println(stack);
            
            }
            
        } else if (ae.getSource() == btnStackMenu) {
            
            frameMenu.setVisible(true);
            frameStack.setVisible(false);
            
        }

    }
    
    public class runnables implements Runnable {
        
        /*
        Comment and Remark - 
        As for this part, intInput1 and intInput2 is collected from JTextField to
        determine how many time the number has to be multiplied.
        */
        
        @Override
        public void run() {
            for (int i = intInput1; i <= intInput2 + 1; i++) {
                
                /*
                Comment and Remark - 
                currentCalculation = i * i meaning it will multiple the number based 
                on the for loop above.
                */
                currentCalculation = i * i;
                storedCalculation = currentCalculation - 1;
                
                /*
                Comment and Remark - 
                After the calculation, save the integear into String viarable, to 
                be display in JTextField.
                */
                
                String display = Integer.toString(storedCalculation);
                            
                txtThreadThread.setText(display);
                System.out.println("[THREAD] = " + storedCalculation);
                                
                try {
                } catch (Exception ex) {
                    
                    System.out.println("[WARN] - Unexpected Error - " + ex);
                    
                }

            }
            
            /*
            Comment and Remark - 
            Once again, store integear calculation into String viarable to be
            display in JTextField.
            */
            
            System.out.println("[FINALLY] = " + storedCalculation);
            String display = Integer.toString(storedCalculation);
            txtThreadThread.setText(display);
                        
        }

    }
    
}
