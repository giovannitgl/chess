import piece.*;
import java.awt.Point;
import java.util.ArrayList;

public class Tabuleiro{
	//Player p1
	//Player p2
	// Point 
	protected Piece[][] tabuleiro;
	Tabuleiro(){
		tabuleiro = new Piece[8][8];
		buildTabuleiro();
	}

	public void buildTabuleiro(){
		buildWhite();
		buildBlack();
	}

	private void buildWhite(){
		for(int i = 0; i < 8; i++){
			Peao p = new Peao(6,i,0);
			tabuleiro[6][i] = p;
		}

		Torre t1 = new Torre(7,0,0);
		Torre t2 = new Torre(7,7,0);
		tabuleiro[7][0] = t1;
		tabuleiro[7][7] = t2;

		Cavalo c1 = new Cavalo(7,1,0);
		Cavalo c2 = new Cavalo(7,6,0);
		tabuleiro[7][1] = c1;
		tabuleiro[7][6] = c2;

		Bispo b1 = new Bispo(7,2,0);
		Bispo b2 = new Bispo(7,5,0);	
		tabuleiro[7][2] = b1;
		tabuleiro[7][5] = b2;

		tabuleiro[7][3] = new Rainha(7,3,0);
		tabuleiro[7][4] = new Rei(7,4,0);
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

	public boolean isPlayerPiece(int x, int y,int p){
		if(tabuleiro[x][y].getTeam() == p)
			return true;
		else
			return false;
	}
}