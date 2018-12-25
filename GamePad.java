

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author prasad
 */
public class GamePad extends javax.swing.JFrame {

    private int draw;
    private int count; //count for detect the player
    private int[][] mat1, mat2; //create a matrices
    JFrame frame = new JFrame("Tic Tac Toe");// create a frame called Tic Tac Toe
    JPanel panel = new JPanel();
    JButton[] btn = new JButton[9]; //create a button array
    int click[] = new int[9]; //create a integer array to store buttons state

    public GamePad(int[][] matrix1, int[][] matrix2) {
        this.mat1 = matrix1;
        this.mat2 = matrix2;
        this.draw = 0;
        this.count = 0;
        //initialize he button array
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton("");
            click[i] = 0;
        }

        panel.setLayout(new GridLayout(3, 3, 0, 0));//set layout
        for (int i = 0; i < btn.length; i++) {
            panel.add(this.btn[i]); //add button to the panel
            btn[i].setPreferredSize(new Dimension(100, 100));//set dimension of a button
        }
        frame.add(panel); //add panel to the frame
        frame.setLocation(512, 150);
        this.component(); //set button action listners

    }

    private GamePad() {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void component() {
        for (int i = 0; i < btn.length; i++) {
            int val = i;
            this.btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (click[val] == 0) {  //run for click[i]=0 only
                        click[val] = 1;

                        buttonClicked(btn[val], val + 1);//perform the process
                    }
                }
            });
        }
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

    private void buttonClicked(JButton button, int n) {
        int row;
        int column;

        //identifying clicked button
        if (n % 3 == 0) {
            column = 2;
            row = n / 3 - 1;

        } else {
            column = n % 3 - 1;
            row = n / 3;
        }

        if (this.count % 2 == 0) {
            button.setText("1");
            this.mat1[row][column] = 1; //when clicked a button correspoding matrix element mark as 1
            int val = WinningCond(mat1); //check whether player 1 wins or not
            if (val == 0) {
                this.count++; //count for detect player
                this.draw++; //count for draw case
            } else {
                msg("Player 1 Wins!!!"); //set message when player 1 wins
            }
        } else {
            button.setText("2");
            this.mat2[row][column] = 1;
            int val = WinningCond(mat2); //check whether player 2 wins or not 
            if (val == 0) {
                this.count++;  //count for detect player
                this.draw++; //count for draw case
            } else if (val == 1) {
                msg("Player 2 Wins!!!"); //set message when player 2 wins
            }
        }

        if (draw == 9) {
            msg("Draw"); //set message when game is draw
        }
    }

    //display winner or draw in message box
    public void msg(String str) {
        final JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(pane, str, "Message", JOptionPane.INFORMATION_MESSAGE);
        int returnVal = pane.showConfirmDialog(null, "Do You Like to Play Again?", "Choice", JOptionPane.YES_NO_OPTION);
        if (returnVal == JOptionPane.YES_OPTION) {
            this.reset();
        } else {
            System.exit(0);
        }
    }

    private int WinningCond(int[][] mat) {
        if (sumArray(mat, 0, 0) == 3 || sumArray(mat, 1, 0) == 3 || sumArray(mat, 2, 0) == 3) { //row check
            return 1;
        } else if (sumArray(mat, 0, 1) == 3 || sumArray(mat, 1, 1) == 3 || sumArray(mat, 2, 1) == 3) { //column check
            return 1;
        } else if (sumArray(mat, 1, 2) == 3) { //main diagonal check
            return 1;
        } else if (sumArray(mat, 1, 3) == 3) { //secondary diagonal check
            return 1;
        }
        return 0;
    }

    private int sumArray(int[][] mat, int n, int c) {
        int sum = 0, val = 0;
        //c=0 row constant
        //c=1 column constant
        //c=2 main diagonal
        //c=3 secondary diagonal
        if (c == 0) {
            for (int i = 0; i < 3; i++) {
                sum += mat[n][i];
            }
        } else if (c == 1) {
            for (int i = 0; i < 3; i++) {
                sum += mat[i][n];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) {
                        sum += mat[i][j];
                        val += mat[i][2 - j];
                    }
                }
            }
        }
        if (c == 3) {
            return val;
        }
        return sum;
    }

    public void reset() {
        System.out.println("in");
        this.draw = 0;
        this.count = 0;

        for (int i = 0; i < this.click.length; i++) { //set all elements 0 in click array
            this.click[i] = 0;
        }

        for (int i = 0; i < btn.length; i++) { //set all button text to null
            this.btn[i].setText("");
        }

        for (int i = 0; i < 3; i++) {      //set all element 0 in mat1 and mat2 
            for (int j = 0; j < 3; j++) {
                this.mat1[i][j] = 0;
                this.mat2[i][j] = 0;
            }
        }
    }

}

