import javax.swing.*;
import java.awt.Color;
import piece.PieceType;

public class View{
    private BoardPanel [][] panels = new BoardPanel[8][8];
    private BoardFrame f;
    
    public void show(BoardFrame f){
        this.f.pack();
        this.f.setVisible(true);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    View(Controller controller){
        f = new BoardFrame();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Color clr;
                switch((i+j)%2){
                    case 0: clr = Color.WHITE;
                            break;
                    case 1: clr = new Color(100,100,100);
                            break;
                    default: clr = new Color(240,20,20);
                }
                panels[i][j] = new BoardPanel(clr, i, j);
                panels[i][j].addMouseListener(controller);
                f.getContentPane().add(panels[i][j]);

            }
        }
        show(f);
    }
    public void clearOneRende(int x, int y){
    	//Unrender one chess piece
    	this.panels[x][y].removeAll();
    }
    public void clearAllRender(){
    	//Unrender all chess pieces
    	for(int i = 0; i < 8; i ++){
    		for(int j = 0; j < 8; j++){
    			this.panels[i][j].removeAll();
    		}
    	}
    }
    public void render(){
    	//renders the screen
    	this.show(f);
    }
    public void addPiece(int x, int y, PieceType t, int player){
        JLabel pieceIcon = null;
        switch(player){
            case 0:
                switch(t){
                    case PAWN:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whitePawn.png")));
                    break;
                    case KNIGHT:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteKnight.png")));
                    break;
                    case BISHOP:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteBishop.png")));
                    break;
                    case ROOK:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteRook.png")));
                    break;
                    case QUEEN:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteQueen.png")));
                    break;
                    case KING:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/whiteKing.png")));
                    break;
        			}
        		break;
            case 1:
                 switch(t){
                    case PAWN:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackPawn.png")));
                    break;
                    case KNIGHT:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackKnight.png")));
                    break;
                    case BISHOP:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackBishop.png")));
                    break;
                    case ROOK:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackRook.png")));
                    break;
                    case QUEEN:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackQueen.png")));
                    break;
                    case KING:
                        pieceIcon = new JLabel(new ImageIcon(getClass().getResource("/icons/images/blackKing.png")));
                    break;
        			}
        	break;
    	}
        if(pieceIcon != null)
            panels[x][y].add(pieceIcon);   
	}
}
