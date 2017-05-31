import javax.swing.*;
import java.awt.Color;

public class Chess {
    private BoardPanel [][] panels = new BoardPanel[8][8];
    private BoardFrame f;
    public void show(){
        this.f.pack();
        this.f.setVisible(true);
    }
    Chess(){
        f = new BoardFrame();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Color clr;
                switch((i+j)%2){
                    case 0: clr = Color.WHITE;
                            break;
                    case 1: clr = Color.BLACK;
                            break;
                    default: clr = Color.BLACK;
                }
                panels[i][j] = new BoardPanel(clr);
                f.getContentPane().add(panels[i][j]);
            }
        }
    }
    public static void main(String [] args){
        Chess c = new Chess();
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                c.show();
            }
        });
        c.show();
    }

}
