import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Model {

    private int draw, count;  //count for detect the player and draw for check draw condition
    private int[][] mat1={{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}; //create and initialize the array for player 1
    private int[][] mat2={{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}; //create and initialize the array for player 2

    public Model() {
        this.draw = 0;
        this.count = 0;
    }
  
    void buttonClicked(JButton button, int n) {
        int row;
        int column;
        String str;
        //identifying clicked button
        if (n % 3 == 0) {
            column = 2;
            row = n / 3 - 1;
        } else {
            column = n % 3 - 1;
            row = n / 3;
        }
        
        
        if (this.count % 2 == 0) {
            button.setText("1");//set button text to 1
            this.mat1[row][column] = 1; //when clicked a button correspoding matrix element mark as 1
            int val = this.WinningCond(this.mat1); //check whether player 1 wins or not
            if (val == 0) {
                this.count++; //count for detect player
                this.draw++; //count for draw case
            } else {
                msg("Player 1 Wins!!!"); //set message when player 1 wins
            }
        } else {
            button.setText("2");//set button text to 2
            this.mat2[row][column] = 1;
            int val = this.WinningCond(this.mat2); //check whether player 2 wins or not 
            if (val == 0) {
                this.count++;  //count for detect player
                this.draw++; //count for draw case
            } else if (val == 1) {
                msg("Player 2 Wins!!!"); //set message when player 2 wins
            }
        }
       
        if (this.draw == 9) {
            msg("Draw"); //set message when game is draw
        }
    }

    //check winning conditions
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
        switch (c) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    sum += mat[n][i];
                }   break;
            case 1:
                for (int i = 0; i < 3; i++) {
                    sum += mat[i][n];
                }   break;
            default:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == j) {
                            sum += mat[i][j];
                            val += mat[i][2 - j];
                        }
                    }
                }   break;
        }
        if (c == 3) {
            return val;
        }
        return sum;
    }

    
    public void reset() { //resset all variables
        this.draw = 0;
        this.count = 0;
        
        Control.buttonReset();//reset buttons

        for (int i = 0; i < 3; i++) {      //set all element 0 in mat1 and mat2 
            for (int j = 0; j < 3; j++) {
                this.mat1[i][j] = 0;
                this.mat2[i][j] = 0;
            }
        }
    }
    
     //method to display winner or draw
     public void msg(String str) {
        JOptionPane.showMessageDialog(null, str, "Message", JOptionPane.INFORMATION_MESSAGE);
        int returnVal = JOptionPane.showConfirmDialog(null, "Do You Like to Play Again?", "Choose", JOptionPane.YES_NO_OPTION);
        if (returnVal == JOptionPane.YES_OPTION) {
            this.reset(); //reset for play again the game
        } else {
            System.exit(0); //exit from the system
        }
    }


}

