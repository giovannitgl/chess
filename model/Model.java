package model;
import gui.*;
import piece.*;
public final class Model{
	private  Tabuleiro t;
	private View v;
	private int currentTurn;
	private int selX,selY;
    private int destX,destY;
	private int dragX,dragY;
	private int mode;
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

	private void setMode(int mode){
		this.mode = mode;
	}

	public void clickedPanel(int x, int y){
		if(mode == 0){
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
		else if(mode == 1){
			if(currentTurn == 0){
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
	   		else{
	   			System.out.println("CHUPA PORRA! AGORA É AI NO COMANDO");
	   			t.bestMove(x, y);
	   			int xx = t.getBestX();
	   			int yy = t.getBestY();
	   			Piece pp = t.getBestPiece();
	   			v.clearOneRende((int)pp.getLocation().getX(),(int)pp.getLocation().getY());
	   			t.changePosition(xx, yy, pp);
	   			v.clearAllRender();
				this.buildIcons();
	   			currentTurn++;
	   			currentTurn = currentTurn%2;
	   		}
		}
	}

	public void clickedMenu(int x){
		if (x == 0){
			v.dispose();
			this.buildTabuleiro(0);
			this.show();
			setMode(0);
		}
		if(x == 1){
			v.dispose();
			this.buildTabuleiro(1);
			this.show();
			setMode(1);
		}
	}
	public void buildTabuleiro(int x){
		t = new Tabuleiro(x);
		v.createTable();
		this.buildIcons();
		// }
		// v.selectTile(1,1);
		// v.desselectTile(1,1);
		// v.render();
	}
	private void buildIcons(){
		for(int i = 0; i < 8; i++){
			System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			for(int j = 0; j < 8; j++){
				if(t.tabuleiro[i][j].getPiece() != null){
					System.out.println("TIPO = " + t.tabuleiro[i][j].getPiece().getType());
					System.out.print("X = " + t.tabuleiro[i][j].getPiece().getLocation().getX());
					System.out.println("  Y = " + t.tabuleiro[i][j].getPiece().getLocation().getY());
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
	public void cursorReleased(){
		v.changeCursor();
		v.setPieceVisibility(dragX,dragY,true);
	}
	private void show(){
		v.show();
	}
}
