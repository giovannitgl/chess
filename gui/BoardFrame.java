package gui;
import javax.swing.JFrame;
import java.awt.GridLayout;
public class BoardFrame extends JFrame{
    GridLayout layout = new GridLayout(8,8); 
    BoardFrame(){
        this.setLayout(layout);
        this.setSize(720,720);
        this.setTitle("Chess");
    }
}
