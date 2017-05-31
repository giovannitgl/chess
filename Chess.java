import javax.swing.*;
import java.awt.Color;

public class Chess {
    private BoardPanel [][] panels = new BoardPanel[8][8];
    private BoardFrame f;
    
    public void show(BoardFrame f){
        this.f.pack();
        this.f.setVisible(true);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Chess(Controller controller){
        f = new BoardFrame();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Color clr;
                switch((i+j)%2){
                    case 0: clr = Color.WHITE;
                            break;
                    case 1: clr = new Color(74,45,3);
                            break;
                    default: clr = new Color(240,20,20);
                }
                panels[i][j] = new BoardPanel(clr, i, j);
                panels[i][j].addMouseListener(controller);
                placeInitialImages(panels[i][j], i, j);
                f.getContentPane().add(panels[i][j]);

            }
        }
        show(f);
    }

    public void placeInitialImages(BoardPanel panels,int x, int y)
    {
        JLabel picLabel = null;
        if (x == 1)
            picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackPawn.png")));
        else if (x == 0)
        {
            if (y == 0 || y == 7)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackRook.png")));
            else if (y == 1 || y == 6)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackKnight.png")));
            else if (y == 2 || y == 5)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackBishop.png")));
            else if (y == 4)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackKing.png")));
            else
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackQueen.png")));
        }  
        else if (x == 6)
            picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whitePawn.png")));
        else if (x == 7)
        {
            if (y == 0 || y == 7)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteRook.png")));
            else if (y == 1 || y == 6)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteKnight.png")));
            else if (y == 2 || y == 5)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteBishop.png")));
            else if (y == 4)
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteKing.png")));
            else
                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteQueen.png")));
        }
        if (picLabel != null)
            panels.add(picLabel);
    }

    //public static void main(String [] args){
        //Chess c = new Chess();
        // javax.swing.SwingUtilities.invokeLater(new Runnable(){
        //     public void run(){
        //         c.show();
        //     }
        // });
        // c.show();
    //}

}
