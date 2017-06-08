package model;
import gui.*;
import piece.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
public final class Model{
	private  Tabuleiro t;
	ServerSocket server;
	Socket client;
	private View v;
	private int currentTurn;
	private int selX,selY;
    private int destX,destY;
	private int dragX,dragY;
	private final int PORT = 5000;
	RoundState rs;
	private static final Model INSTANCE = new Model();
	private Model(){
		currentTurn = 0;
		rs = RoundState.NOCLICK;
	}
	public static Model getInstance(){
		return INSTANCE;
	}
	public void setView(View v){
		this.v = v;
	}

	public void clickedPanel(int x, int y){
		switch(rs){
			case NOCLICK:
				if(t.isPlayerPiece(x,y,currentTurn)){
					v.selectTile(x,y);
					selX = x;
					selY = y;
					rs = RoundState.FIRSTCLICK;
          System.out.println(rs);
				}
			break;
			case FIRSTCLICK:
        // Deseleciona
        if (x == selX && y == selY) {
          v.desselectTile(x,y);
          rs = RoundState.NOCLICK;
          System.out.println(rs);
          break;
        }
        // Reseleciona
        if (t.isPlayerPiece(x,y,currentTurn)) {
          v.desselectTile(selX,selY);
          v.selectTile(x,y);
					selX = x;
					selY = y;
          rs = RoundState.FIRSTCLICK;
          System.out.println(rs);
          break;
				}
        // Escolhe Dest
        if (!t.isPlayerPiece(x,y,currentTurn) && t.isValid(x, y, t.tabuleiro[selX][selY].getPiece()) ) {
          Piece p = t.tabuleiro[selX][selY].getPiece();
          System.out.println("Valido");
          v.desselectTile(selX,selY);
          v.addPiece(x,y,t.tabuleiro[selX][selY].getPiece().getType(),t.tabuleiro[selX][selY].getPiece().getTeam());
          v.clearOneRende(selX,selY);
          System.out.println("TIME PORRA =" + t.tabuleiro[selX][selY].getPiece().getTeam());
          t.changePosition(x,y,p);
          System.out.println("TIME = " + t.tabuleiro[x][y].getPiece().getTeam());
          currentTurn++;
          currentTurn = currentTurn % 2;
          rs = RoundState.NOCLICK;
          v.clearAllRender();
          this.buildIcons();
          break;
        }
        else {
          System.out.println("Invalido");
          v.desselectTile(selX,selY);
          rs = RoundState.NOCLICK;
          break;
        }
    }
	}

	public void clickedMenu(int x){
		if (x == 0){
			v.dispose();
			this.buildTabuleiro();
			this.show();
		}
		if (x == 2){
			v.dispose();
			v.createMPMenu();
			this.show();
		}
	}
	public void mpClickedMenu(int x){
		if (x == 0){
			v.dispose();
			this.waitScreen();
			this.startHost();
		}
		if (x == 1){
			v.makeDialog();
		}
		else if (x == 2){
			v.dispose();
			v.createMenu();
			this.show();
		}
	}
	public void buildTabuleiro(){
		t = new Tabuleiro();
		v.createTable();
		this.buildIcons();
		// }
		// v.selectTile(1,1);
		// v.desselectTile(1,1);
		// v.render();
	}
	private void buildIcons(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(t.tabuleiro[i][j].getPiece() != null){
					v.addPiece(i,j,t.tabuleiro[i][j].getPiece().getType(), t.tabuleiro[i][j].getPiece().getTeam());
				}
			}
		}
	}
	public void overText(int x){
		v.highlight(x);
	}
	public void leftText(int x){
		v.unhighlight(x);
	}
	public void cursorPressed(int x, int y){
		if(t.getTeam(x,y) != -1){
			v.changeCursor(t.getType(x,y), t.getTeam(x,y));
			v.setPieceVisibility(x,y,false);
			dragX = x;
			dragY = y;

		}
	}
	private void waitScreen(){
		v.waitScreen();
	}
	public void cursorReleased(){
		v.changeCursor();
		v.setPieceVisibility(dragX,dragY,true);
	}
	private void show(){
		v.show();
	}

	private void startHost(){
		try{
			server = new ServerSocket(PORT);
		}
		catch(IOException e){
			System.out.println(e);
		}
		try{
			client = server.accept();
		}
		catch(IOException e){
			System.out.println(e);
		}
		v.dispose();
		this.buildTabuleiro();
		this.show();
	}

	public void joinConnection(String s){
		try{
			client = new Socket(s,PORT);
		}
		catch(IOException e){
			System.out.println(e);
		}
		if(client.isConnected()){
			v.dispose();
			this.buildTabuleiro();
			this.show();
		}
	}
}
