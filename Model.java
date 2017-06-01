public class Model{
	private Tabuleiro t;
	View v;
	Model(View v){
		this.v = v;
		this.buildTabuleiro();
	}
	public void buildTabuleiro(){
		this.v = v;
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