public final class Model{
	private  Tabuleiro t;
	private View v;
	private static final Model INSTANCE = new Model();
	private Model(){
	}
	public static Model getInstance(){
		return INSTANCE;
	}
	public void setView(View v){
		this.v = v;
	}
	public void buildTabuleiro(){
		t = new Tabuleiro();
		t.buildTabuleiro();
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
}