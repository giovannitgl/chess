import javax.swing.JFrame;
import java.awt.GridLayout;
public class BoardFrame extends JFrame{
    GridLayout layout = new GridLayout(8,8); 
    BoardFrame(){
        this.setLayout(layout);
            //this.setSize(300,400);
        this.setTitle("Chess");
    }
}
