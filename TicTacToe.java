

/**
 *
 * @author prasad
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int [][]a={{0,0,0},{0,0,0},{0,0,0}}; //initializing matrix for player1
       int [][]b={{0,0,0},{0,0,0},{0,0,0}}; //initializing matrix for player2
       GamePad g =new GamePad(a,b); //create object from Gamepad
       g.setVisible(true);
    }
    
}

