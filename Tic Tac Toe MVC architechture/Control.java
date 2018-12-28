import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Control {   
    private static JButton []b; //create a button array
    private static int[] click={0,0,0,0,0,0,0,0,0}; //create a integer array to store buttons state
    Model model=new Model();
    
    
    public Control(JButton []button)  {
      this.b=button;      //initialize button array
    }
     

   //set action listners for buttons  
    public void component() { 
        for (int i = 0; i < this.b.length; i++) {
            int val = i;
            JButton btemp=b[i];
           
            btemp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    
                    if (click[val] == 0) {  //run for click[i]=0 only
                        click[val] = 1;
                        model.buttonClicked(btemp, val + 1);//perform the action
                    }
                }
            });
        }
    }

     
    public static void buttonReset() {
         for (int i = 0; i < Control.b.length; i++) { 
            Control.b[i].setText(""); //set all button text to null
            Control.click[i] = 0; //set button state array to zero
        }
    }
    
}


