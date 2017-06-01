import piece.*;
import java.awt.Point;
import java.util.ArrayList;

public class Tabuleiro{
	//Player p1
	//Player p2
	Point 
	protected Piece[][] tabuleiro;
	Tabuleiro(){
		tabuleiro = new Piece[8][8];
	}

	public void buildTabuleiro(Piece[][] tabuleiro){
		buildWhite(tabuleiro);
		buildBlack(tabuleiro);
	}

	private void buildWhite(Piece[][] tabuleiro){
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
	}

	private void buildBlack(Piece[][] tabuleiro){
		for(int i = 0; i < 8; i++){
			Peao p = new Peao(1,i,0);
			tabuleiro[6][i] = p;
		}

		Torre t1 = new Torre(0,0,0);
		Torre t2 = new Torre(0,7,0);
		tabuleiro[7][0] = t1;
		tabuleiro[7][7] = t2;

		Cavalo c1 = new Cavalo(0,1,0);
		Cavalo c2 = new Cavalo(0,6,0);
		tabuleiro[7][1] = c1;
		tabuleiro[7][6] = c2;

		Bispo b1 = new Bispo(0,2,0);
		Bispo b2 = new Bispo(0,5,0);	
		tabuleiro[7][2] = b1;
		tabuleiro[7][5] = b2;
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
}