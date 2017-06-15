package gui;
import javax.swing.*;
import java.awt.Color;
import piece.PieceType;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JDialog;
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class View{
    private BoardPanel [][] panels = new BoardPanel[8][8];
    private JFrame f;
    private JPanel cards;
    private JPanel currentPanel;
    private JLabel p_turn;
    private JLabel checkText;
    private Controller control;
    public void show(){
        this.f.pack();
        this.f.setVisible(true);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void cardsMake(){
        cards = new JPanel(new CardLayout());
        cards.add(createMenu(),"MENU");
        cards.add(createTable(),"TABLE");
        cards.add(createMPMenu(),"MP");
        cards.add(waitScreen(),"WAIT");
    }

    public View(Controller controller){
        this.control = controller;
        f = new JFrame("Chess");
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardsMake();
        f.getContentPane().add(cards);
        CardLayout l = (CardLayout) cards.getLayout();
        l.show(cards,"MENU");
        this.show();
    }
    public JPanel createMenu(){
        JPanel f = new JPanel();
        MenuText [] menuText = new MenuText[3];
        JPanel [] menuPanel = new JPanel[2];
        GridLayout layout = new GridLayout(3,0,-1,1);
        f.setLayout(new GridLayout(2,0));
        menuPanel[0] = gameTitle();
        menuPanel[1] = new JPanel();
        menuPanel[1].setLayout(layout);
        menuPanel[1].setBorder(BorderFactory.createLineBorder(Color.WHITE));
        menuPanel[1].setBackground(Color.BLACK);
        menuText[0] = new MenuText("Player vs Player",0);
        menuText[1] = new MenuText("Player vs AI",1);
        menuText[2] = new MenuText("Player vs Player (Online)",2);
        for (int i = 0; i < 3; i++){
            menuText[i].setForeground(Color.WHITE);
            menuPanel[1].add(menuText[i]);
            menuText[i].addMouseListener(control);
        }
        f.add(menuPanel[0]);
        f.add(menuPanel[1]);
        return f;
    }
    public JPanel createTable(){
        JPanel f = new JPanel(new BorderLayout());
        p_turn = new JLabel();
        checkText = new JLabel();
        this.setPlayerTurn(0);
        // this.setCheck(true,0);
        JPanel header = new JPanel(new BorderLayout());
        header.add(p_turn,BorderLayout.LINE_START);
        header.add(checkText,BorderLayout.LINE_END);
        header.setBackground(new Color(200,100,10));
        f.add(header,BorderLayout.PAGE_START);
        JPanel board = new JPanel(new GridLayout(8,8));
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
                board.add(panels[i][j]);

            }
        }
        f.add(board, BorderLayout.CENTER);
        return f;
    }
    public void setPlayerTurn(int x){
        if(x == 0){
            p_turn.setText("<html>Player Turn: <font color=white>White</font>");
        }
        else{
            p_turn.setText("Player Turn: Black");

        }
    }
    public void setCheck(boolean t, int x){
        if (!t){
            this.checkText.setText(" ");
        }
        else{
            if(x == 0){
                checkText.setText("<html><font color = white> White King</font> in Check</html>");
            }
            else{
                checkText.setText("Black King in Check");
            }
        }
    }
    public JPanel createMPMenu(){
        JPanel f = new JPanel(new GridLayout(2,0));
        MenuText [] menuText = new MenuText[3];

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3,0));
        p.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        p.setBackground(Color.BLACK);
        MenuText [] options = new MenuText[2];
        menuText[0] = new MenuText("Host Game",0);
        menuText[1] = new MenuText("Join Game",1);
        menuText[2] = new MenuText("Return",2);
        for(int i = 0; i < 3; i++){
            menuText[i].setForeground(Color.WHITE);
            p.add(menuText[i]);
            menuText[i].addMouseListener(control);
        }
        f.add(gameTitle());
        f.add(p);
        return f;
    }
    public JPanel waitScreen(){
        JPanel f = new JPanel(new GridLayout(2,0));
        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        JLabel t = new JLabel("     Waiting...      ");
        t.setFont(new Font("Serif", Font.BOLD, 30));
        t.setForeground(Color.WHITE);
        p.add(t);
        f.add(gameTitle());
        f.add(p);
        return f;
    }
    public void setMenu(){
        CardLayout l = (CardLayout) cards.getLayout();
        l.show(cards,"MENU");

    }
    public void setTable(){
        CardLayout l = (CardLayout) cards.getLayout();
        l.show(cards,"TABLE");
    }
    public void setMPMenu(){
        CardLayout l = (CardLayout) cards.getLayout();
        l.show(cards,"MP");
    }
    public void setWaitScreen(){
        CardLayout l = (CardLayout) cards.getLayout();
        l.show(cards,"WAIT");
    }
    private JPanel gameTitle(){
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.add(new JLabel (new ImageIcon(getClass().getResource("/icons/menu.png"))));
        return menuPanel;
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
    // }
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
    public void makeDialog(){
        String s = JOptionPane.showInputDialog(this.f,"Enter IP","127.0.0.1");
        control.receivedIP(s);

    }
    public void repaint(){
        this.cards.repaint();
    }
}
