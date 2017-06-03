import piece.*;
import java.awt.Point;
import java.util.ArrayList;

public final class Model{
	private Tabuleiro t;
	private View v;
	private int currentTurn;
	private int selX,selY;
	private int dragX,dragY;
	private ArrayList<Point> pieceList;
	enum RoundState{
		NOCLICK, FIRSTCLICK
	} 
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
		System.out.println(rs);
		switch(rs){
			case NOCLICK:
				if(t.isPlayerPiece(x,y,currentTurn)){
					//System.out.println("ESFANOISFAOIJAOisjfIIII!!!!");		
					//v.selectTile(x,y);
					pieceList = t.evaluatePiece(x,y);
					for(int i = 0; i < pieceList.size(); i++){
						System.out.println("ENTREIIIII!!!!");
						if(t.tabuleiro[(int)pieceList.get(i).getX()][(int)pieceList.get(i).getX()].getTeam() != currentTurn){
							//System.out.println("TESTANDO");
							//System.out.println("X = " + pieceList.get(i).getLocX());
							//System.out.println("Y = " + pieceList.get(i).getLocY());
							v.selectTile((int)pieceList.get(i).getX(),(int)pieceList.get(i).getY());
						}
					}
					selX = x;
					selY = y;
					rs = RoundState.FIRSTCLICK;
				}
			break;
			case FIRSTCLICK:
				for(int i = 0; i < pieceList.size(); i++){
					v.desselectTile((int)pieceList.get(i).getX(),(int)pieceList.get(i).getY());
				}
				// if(isValide(x,y,piece)){
				// 	v.desselectTile(x,y);
				// 	rs = RoundState.NOCLICK;
				// 	currentTurn = (currentTurn + 1) % 2;
				// }
				// else{
					v.desselectTile(selX,selY);
					selX = selY = -1;
					rs = RoundState.NOCLICK;
				// }
			break;
		}
	}
	public void clickedMenu(int x){
		if (x == 0){
			this.buildTabuleiro();
		}
	}
	public void buildTabuleiro(){
		t = new Tabuleiro();
		v.createTable();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(t.tabuleiro[i][j] != null){
					v.addPiece(i,j,t.tabuleiro[i][j].getType(), t.tabuleiro[i][j].getTeam());
				}
			}
		}
		v.selectTile(1,1);
		v.desselectTile(1,1);
		v.render();
	}
	public void overText(int x){
		v.highlight(x);
	}
	public void leftText(int x){
		v.unhighlight(x);
	}
	public void cursorPressed(int x, int y){
		v.changeCursor(t.getType(x,y), t.getTeam(x,y));
		v.setPieceVisibility(x,y,false);
		dragX = x;
		dragY = y;
	}
	public void cursorReleased(){
		v.changeCursor();
		v.setPieceVisibility(dragX,dragY,true);
	}
}