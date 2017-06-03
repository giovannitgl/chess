import piece.*;
import java.awt.Point;
import java.util.ArrayList;

public class Tabuleiro{
	Jogador j0;
	Jogador j1;
	// Point 
	protected Piece[][] tabuleiro;
	Tabuleiro(){
		tabuleiro = new Piece[8][8];
		buildTabuleiro();
	}

	public void buildTabuleiro(){
		j0 = new Jogador(0);
		j1 = new Jogador(1);
		buildWhite();
		buildBlack();
		buildMiddle();
	}

	private void buildWhite(){
		for(int i = 0; i < 8; i++){
			Peao p = new Peao(6,i,0);
			tabuleiro[6][i] = p;
			j0.addPiece(tabuleiro[6][i]);
		}

		Torre t1 = new Torre(7,0,0);
		Torre t2 = new Torre(7,7,0);
		tabuleiro[7][0] = t1;
		tabuleiro[7][7] = t2;
		//j0.addPiece(tabuleiro[7][0]);
		//j0.addPiece(tabuleiro[7][0]);

		Cavalo c1 = new Cavalo(7,1,0);
		Cavalo c2 = new Cavalo(7,6,0);
		tabuleiro[7][1] = c1;
		tabuleiro[7][6] = c2;
		//j0.addPiece(tabuleiro[7][1]);
		//j0.addPiece(tabuleiro[7][6]);

		Bispo b1 = new Bispo(7,2,0);
		Bispo b2 = new Bispo(7,5,0);	
		tabuleiro[7][2] = b1;
		tabuleiro[7][5] = b2;
		//j0.addPiece(tabuleiro[7][2]);
		//j0.addPiece(tabuleiro[7][5]);

		tabuleiro[7][3] = new Rainha(7,3,0);
		tabuleiro[7][4] = new Rei(7,4,0);
		//j0.addPiece(tabuleiro[7][3]);
		//j0.addPiece(tabuleiro[7][4]);
	}

	private void buildBlack(){
		for(int i = 0; i < 8; i++){
			Peao p = new Peao(1,i,1);
			tabuleiro[1][i] = p;
		}

		Torre t1 = new Torre(0,0,1);
		Torre t2 = new Torre(0,7,1);
		tabuleiro[0][0] = t1;
		tabuleiro[0][7] = t2;

		Cavalo c1 = new Cavalo(0,1,1);
		Cavalo c2 = new Cavalo(0,6,1);
		tabuleiro[0][1] = c1;
		tabuleiro[0][6] = c2;

		Bispo b1 = new Bispo(0,2,1);
		Bispo b2 = new Bispo(0,5,1);	
		tabuleiro[0][2] = b1;
		tabuleiro[0][5] = b2;
		tabuleiro[0][3] = new Rainha(0,3,1);
		tabuleiro[0][4] = new Rei(0,4,1);
	}
	private void buildMiddle(){
		for(int i = 2; i < 6; i ++){
			for(int j = 0; j < 8; j++){
				tabuleiro[i][j] = new Piece(i,j,-1);
			}
		}
	}

	public void changePosition(int x, int y, Piece p){
		tabuleiro[p.getLocX()][p.getLocY()].deletePiece();
		tabuleiro[x][y] = p;
		p.setLocation(x,y);
	}

	public boolean isValid(int newX, int newY, Piece p){
		ArrayList<Point> validMoves = p.validMoves;
		Point ponto;
		for(int i = 0; i < validMoves.size(); i++){
			ponto = validMoves.get(i);
			if(newX == (int)ponto.getX() && newY == (int)ponto.getY()){
				//changePosition(x,y,p);
				return true;
			}
		}
		return false;
	}
	public boolean pieceExists(int x, int y){
		if (tabuleiro[x][y] == null)
			return false;
		else
			return true;
	}
	public boolean isPlayerPiece(int x, int y,int p){
		if(tabuleiro[x][y].getTeam() == p)
			return true;
		else
			return false;
	}
	public PieceType getType(int x, int y){
		return tabuleiro[x][y].getType();
	}
	public int getTeam(int x, int y){
		return tabuleiro[x][y].getTeam();
	}

		public ArrayList<Piece> pieceValidMoves(int team){
		if(team == 0){
			return j0.getPlayerPieces();
		}
		else{
			return j1.getPlayerPieces();
		}
	}

	public ArrayList<Point> evaluatePiece(int x, int y){
		tabuleiro[x][y].updatePosition(x,y);
		return tabuleiro[x][y].getValidMoves();

	}
}