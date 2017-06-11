package model;
import gui.*;
import piece.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.Point;

public final class Model{
	private  Tabuleiro t;
	private ServerSocket server;
	private Socket client;
	private boolean multiplayer;
	private boolean isHost;
	private DataOutputStream out;
	private DataInputStream in;
	private Thread synch;
	public View v;
	private Thread roundSych;
	private int currentTurn;
	private int selX,selY;
    private int destX,destY;
	private int dragX,dragY;
	private final int PORT = 5000;
	RoundState rs;
	private static final Model INSTANCE = new Model();
	private Model(){
		currentTurn = 0;
		this.isHost = false;
		rs = RoundState.NOCLICK;
	}
	public static Model getInstance(){
		return INSTANCE;
	}
	public void setView(View v){
		this.v = v;
	}
	public void nextRound(){
		this.currentTurn++;
		this.currentTurn = currentTurn % 2;
	}
	public Piece getPiece(int x,int y){
		return this.t.tabuleiro[x][y].getPiece();
	}
	public void changePosition(int x, int y, Piece p){
		this.t.changePosition(x,y,p);
	}
	public void clickedPanel(int x, int y){
		switch(rs){
			case NOCLICK:
				//condicional checa se a peça é do jogador da vez, o segundo condicional assegura
				//que o round esta condizente com a peça clicada no multiplayer
				if(t.isPlayerPiece(x,y,currentTurn)){
					if(multiplayer){
						if((isHost && currentTurn == 0) ||(!isHost && currentTurn == 1)){
							v.selectTile(x,y);
							selX = x;
							selY = y;
							rs = RoundState.FIRSTCLICK;
						}
					}
					else{
						v.selectTile(x,y);
						selX = x;
						selY = y;
						rs = RoundState.FIRSTCLICK;
		      			// System.out.println(rs);
					}
				}
			break;
			case FIRSTCLICK:
        // Deseleciona
	        if (x == selX && y == selY) {
	          v.desselectTile(x,y);
	          rs = RoundState.NOCLICK;
	          // System.out.println(rs);
	          break;
	        }
	        // Reseleciona
	        if (t.isPlayerPiece(x,y,currentTurn)) {
	        	if(multiplayer){
	        		if((isHost && currentTurn == 0) || (!isHost && currentTurn == 1)){
			          	v.desselectTile(selX,selY);
			         	v.selectTile(x,y);
						selX = x;
						selY = y;
			          	rs = RoundState.FIRSTCLICK;
	        		}
	        	}
	        	else{
			      	v.desselectTile(selX,selY);
			     	v.selectTile(x,y);
					selX = x;
					selY = y;
			      	rs = RoundState.FIRSTCLICK;
	        	}
		      	// System.out.println(rs);
	          	break;
				}
	        // Escolhe Dest
	        if (!t.isPlayerPiece(x,y,currentTurn) && t.isValid(x, y, t.tabuleiro[selX][selY].getPiece()) ) {
	        	if(multiplayer){
	        		sendMove(selX,selY);
	        		sendMove(x,y);
	        	}
			    Piece p = t.tabuleiro[selX][selY].getPiece();
			    // System.out.println("Valido");
			    v.desselectTile(selX,selY);
			    v.addPiece(x,y,t.tabuleiro[selX][selY].getPiece().getType(),t.tabuleiro[selX][selY].getPiece().getTeam());
			    v.clearOneRende(selX,selY);
			    // System.out.println("TIME PORRA =" + t.tabuleiro[selX][selY].getPiece().getTeam());
			    t.changePosition(x,y,p);
			    // System.out.println("TIME = " + t.tabuleiro[x][y].getPiece().getTeam());
			    this.nextRound();
			    if(multiplayer)
				    this.sendNextRound();
			    rs = RoundState.NOCLICK;
			    v.clearAllRender();
			    this.buildIcons();
			    // System.out.println("PRESO?");
			    break;
	        }
	        else {
	          // System.out.println("Invalido");
	          v.desselectTile(selX,selY);
	          rs = RoundState.NOCLICK;
	          break;
	        }
	    }
	}

	public void clickedMenu(int x){
		if (x == 0){
			this.buildTabuleiro();
			this.show();
		}
		if (x == 2){
			v.setMPMenu();
			this.show();
		}
	}
	public void mpClickedMenu(int x){
		if (x == 0){
			v.setWaitScreen();
			this.show();
			this.startHost();
		}
		if (x == 1){
			v.makeDialog();
		}
		else if (x == 2){
			v.setMenu();
			this.show();
		}
	}
	public void buildTabuleiro(){
		t = new Tabuleiro();
		v.setTable();
		this.buildIcons();
		// }
		// v.selectTile(1,1);
		// v.desselectTile(1,1);
		// v.render();
	}
	public void buildIcons(){
		v.clearAllRender();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(t.tabuleiro[i][j].getPiece() != null){
					v.addPiece(i,j,t.tabuleiro[i][j].getPiece().getType(), t.tabuleiro[i][j].getPiece().getTeam());
				}
			}
		}
	}
	// public void overText(int x){
	// 	v.highlight(x);
	// }
	// public void leftText(int x){
	// 	v.unhighlight(x);
	// }
	public void cursorPressed(int x, int y){
		if(t.getTeam(x,y) != -1){
			v.changeCursor(t.getType(x,y), t.getTeam(x,y));
			v.setPieceVisibility(x,y,false);
			dragX = x;
			dragY = y;

		}
	}
	public void cursorReleased(){
		v.changeCursor();
		v.setPieceVisibility(dragX,dragY,true);
	}
	private void show(){
		v.show();
	}

	private void startHost(){
		// server = new ServerThread(this.client, this);
		// thread = new Thread(server);
		// thread.start();
		System.out.println("AQUI");
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
		this.connected();
		try{
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
		}
		catch(IOException e){
			System.out.println(e);
		}
		// this.multiplayer = true;
		this.isHost = true;
		this.connected();
		// do{
		// 	v.show();
			System.out.println(client.isConnected());
		// }while(!client.isConnected());
		// do{
		// 	// v.show();
			System.out.println("Client = null");
		// }while(client == null);
		// v.dispose();
		// this.buildTabuleiro();
		// this.show();
	}
	public void connected(){
		synch = new Thread(new MessageListener(in));
		synch.start();
		this.multiplayer = true;
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
			try{
				in = new DataInputStream(client.getInputStream());
				out = new DataOutputStream(client.getOutputStream());
				
			}
			catch(IOException e){
				System.out.println(e);
			}
			this.connected();
		}
	}
	public void getMove(){
		int x = 0;
		int y = 0;
		try{
			x = in.readInt();
			y = in.readInt();
		}
		catch(IOException e){
			System.out.println(e);
		}
		// System.out.println("Recebi" + x + " " + y);
	}
	public void sendMove(int x, int y){
		try{
			out.writeInt(x);
			out.flush();
			out.writeInt(y);
			out.flush();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	public void sendNextRound(){
		try{
			out.writeInt(10);
			out.writeInt(10);
			out.writeInt(11);
			out.writeInt(11);
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
}
