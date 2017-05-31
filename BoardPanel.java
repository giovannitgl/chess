import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
public class BoardPanel extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    BoardPanel(Color c){
        this.setOpaque(true);
        this.setBackground(c);
    }
}
