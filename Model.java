public final class Model{
	private  Tabuleiro t;
	private View v;
	private int currentTurn;
	private int selX,selY;
  private int destX,destY;
	private int dragX,dragY;
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
        if (!t.isPlayerPiece(x,y,currentTurn) && t.isValid(x, y, t.tabuleiro[selX][selY]) ) {
          System.out.println("Valido");
          v.desselectTile(selX,selY);
          v.addPiece(x,y,t.tabuleiro[selX][selY].getType(),t.tabuleiro[selX][selY].getTeam());
          v.clearOneRende(selX,selY);
          t.changePosition(x,y,t.tabuleiro[selX][selY]);
          rs = RoundState.NOCLICK;
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

}
