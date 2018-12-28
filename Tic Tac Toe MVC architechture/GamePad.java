import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePad extends javax.swing.JFrame {
    Control c;
    Model m;
    JFrame frame = new JFrame("Tic Tac Toe");// create a frame called Tic Tac Toe
    JPanel panel = new JPanel(); //create a panel for the frame
    JButton[] btn = new JButton[9]; //create a button array

    public GamePad() {
        //initialize the button array
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton("");
        }
        
         c= new Control(btn);//create object from controll
         
        panel.setLayout(new GridLayout(3, 3, 0, 0));//set layout
        
        for (int i = 0; i < btn.length; i++) {
            panel.add(this.btn[i]); //add button to the panel
            btn[i].setPreferredSize(new Dimension(100, 100));//set dimension of a button
        }
        
        frame.add(panel); //add panel to the frame
        frame.setLocation(512, 150); //set frame location
        c.component(); //set button action listners
    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GamePad().setVisible(true);
            }
        });

    }

    @Override
    public void setVisible(boolean b) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}


