import javax.swing.*;
import java.awt.Color;
import piece.PieceType;
import javax.swing.BorderFactory;
import java.awt.GridLayout;

public class View{
    private BoardPanel [][] panels = new BoardPanel[8][8];
    private MenuText [] menuText = new MenuText[3];
    private JPanel [] menuPanel = new JPanel[2];
    private JFrame f;
    private Controller control;
    public void show(){
        this.f.pack();
        this.f.setVisible(true);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    View(Controller controller){
        this.control = controller;
         createMenu();
    }
    public void createMenu(){
        f = new JFrame();
        GridLayout layout = new GridLayout(3,0,-1,1);
        f.setTitle("Chess");
        menuPanel[0] = new JPanel();
        menuPanel[1] = new JPanel();
        // layout.setVgap(1);
        menuPanel[1].setLayout(layout);
        for (int i = 0; i < 2; i++)
            this.menuPanel[i].setBackground(Color.BLACK);
        menuPanel[0].add(new JLabel(new ImageIcon(getClass().getResource("/icons/menu.png"))));
        menuText[0] = new MenuText("Player vs Player",0);
        menuText[1] = new MenuText("Player vs AI",1);
        menuText[2] = new MenuText("Player vs Player (Online)",2);
        // menuPanel[1].addMouseListener(control);
        for (int i = 0; i < 3; i++){
            menuText[i].setForeground(Color.WHITE);
            menuPanel[1].add(menuText[i]);
            menuText[i].addMouseListener(control);
        }
        f.getContentPane().add(menuPanel[0]);
        f.getContentPane().add(menuPanel[1]);
        show();
    }
    public void createTable(){
        JFrame f = new JFrame();
        f.setTitle("Chess");
        f.setLayout(new GridLayout(8,8));
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
                panels[i][j].addMouseListener(control);
                f.getContentPane().add(panels[i][j]);

            }
        }
        this.f = f;
        show();
    }
    public void selectTile(int x, int y){
    	panels[x][y].setBackground(Color.YELLOW);
    	panels[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public void desselectTile(int x, int y){
    	if((x+y)%2 == 0){
    		panels[x][y].setBackground(Color.WHITE);
    	}
    	else
    		panels[x][y].setBackground(new Color(100,100,100));
    	panels[x][y].setBorder(null);
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
    	this.show();
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
