import piece.*;
import java.awt.Point;
import java.util.ArrayList;

public class Tabuleiro{
	Jogador j0;
	Jogador j1;
	// Point
	// protected Piece[][] tabuleiro;
	protected Space[][] tabuleiro;
	Tabuleiro(){
		tabuleiro = new Space[8][8];
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
			// Peao p = new Peao(6,i,0);
			tabuleiro[6][i] = new Space(new Peao(6,i,0));
			j0.addPiece(tabuleiro[6][i].getPiece());
		}
		// Torre t1 = new Torre(7,0,0);
		// Torre t2 = new Torre(7,7,0);
		tabuleiro[7][0] = new Space(new Torre(7,0,0));
		tabuleiro[7][7] = new Space(new Torre(7,7,0));
		j0.addPiece(tabuleiro[7][0].getPiece());
		j0.addPiece(tabuleiro[7][7].getPiece());

		// Cavalo c1 = new Cavalo(7,1,0);
		// Cavalo c2 = new Cavalo(7,6,0);
		tabuleiro[7][1] = new Space(new Cavalo(7,1,0));
		tabuleiro[7][6] = new Space(new Cavalo(7,6,0));
		j0.addPiece(tabuleiro[7][1].getPiece());
		j0.addPiece(tabuleiro[7][6].getPiece());

		// Bispo b1 = new Bispo(7,2,0);
		// Bispo b2 = new Bispo(7,5,0);
		tabuleiro[7][2] = new Space(new Bispo(7,2,0));
		tabuleiro[7][5] = new Space(new Bispo(7,5,0));
		j0.addPiece(tabuleiro[7][2].getPiece());
		j0.addPiece(tabuleiro[7][5].getPiece());

		tabuleiro[7][3] = new Space(new Rainha(7,3,0));
		tabuleiro[7][4] = new Space(new Rei(7,4,0));
		j0.addPiece(tabuleiro[7][3].getPiece());
		j0.addPiece(tabuleiro[7][4].getPiece());
	}

	private void buildBlack(){
		for(int i = 0; i < 8; i++){
			// Peao p = new Peao(1,i,1);
			tabuleiro[1][i] = new Space(new Peao(1,i,1));
			j1.addPiece(tabuleiro[1][i].getPiece());
		}

		// Torre t1 = new Torre(0,0,1);
		// Torre t2 = new Torre(0,7,1);
		tabuleiro[0][0] = new Space(new Torre(0,0,1));
		tabuleiro[0][7] = new Space(new Torre(0,7,1));
		j1.addPiece(tabuleiro[0][0].getPiece());
		j1.addPiece(tabuleiro[0][7].getPiece());

		// Cavalo c1 = new Cavalo(0,1,1);
		// Cavalo c2 = new Cavalo(0,6,1);
		tabuleiro[0][1] = new Space(new Cavalo(0,1,1));
		tabuleiro[0][6] = new Space(new Cavalo(0,6,1));
		j1.addPiece(tabuleiro[0][1].getPiece());
		j1.addPiece(tabuleiro[0][6].getPiece());

		// Bispo b1 = new Bispo(0,2,1);
		// Bispo b2 = new Bispo(0,5,1);
		tabuleiro[0][2] = new Space(new Bispo(0,2,1));
		tabuleiro[0][5] = new Space(new Bispo(0,5,1));
		j1.addPiece(tabuleiro[0][2].getPiece());
		j1.addPiece(tabuleiro[0][5].getPiece());

		tabuleiro[0][3] = new Space(new Rainha(0,3,1));
		tabuleiro[0][4] = new Space(new Rei(0,4,1));
		j1.addPiece(tabuleiro[0][3].getPiece());
		j1.addPiece(tabuleiro[0][4].getPiece());
	}
	private void buildMiddle(){
		for(int i = 2; i < 6; i ++){
			for(int j = 0; j < 8; j++){
				tabuleiro[i][j] = new Space();
			}
		}
	}

	public void changePosition(int x, int y, Piece p){
		int x1 = p.getLocX();
		int y1 = p.getLocY();
		// if(p.getType() == PieceType.PAWN){
		// 	tabuleiro[x][y] = new Peao(x,y,0);
		// }
		// tabuleiro[x][y].setTeam(p.getTeam());
		// tabuleiro[x][y].setType(p.getType());
		// tabuleiro[x][y].setLocation(p.getLocX(), p.getLocY());
		// tabuleiro[x][y].validMoves = p.validMoves;
		// tabuleiro[x1][y1].deletePiece(x1,y1);		
		// System.out.println("POSICAO ANTIGA: X = " + x1 + " Y = " + y1);
		// System.out.println("NOVA POSICAO: X = " + tabuleiro[x][y].getLocX() + " Y = " + tabuleiro[x][y].getLocY() + " TIME =" + tabuleiro[x][y].getTeam() + " TYPE = " + tabuleiro[x][y].getType());		
		tabuleiro[x][y].setPiece(p);
		tabuleiro[x1][y1].setPiece(null);
		p.setLocation(x,y);
		
		updateValidMove();
	}

	private void updateValidMove(){
		for(int i = 4; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Piece p = tabuleiro[i][j].getPiece();
				if(p != null){
					if(p.getTeam() == 0){
						p.updatePosition(j0.getPlayerPieces(), j1.getPlayerPieces());
					}
					else p.updatePosition(j1.getPlayerPieces(), j0.getPlayerPieces());
					// tabuleiro[i][j].getPiece().updatePosition(i,j);
				}
			}
		}
	}

	public boolean isValid(int newX, int newY, Piece p){
    	// p.updatePosition(p.getLocX(),p.getLocY());
    	System.out.println("NEW X =" + newX + " NEW Y = " + newY);
    	System.out.println("ACT X =" + p.getLocX() + " ACT Y = " + p.getLocY());
    	
		ArrayList<Point> validMoves = p.validMoves;

		for(int i = 0; i < validMoves.size(); i++){
			System.out.println("x = " + validMoves.get(i).getX() + " y = " + validMoves.get(i).getY());
		}

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
		if(tabuleiro[x][y].getPiece() != null){
			if(tabuleiro[x][y].getPiece().getTeam() == p){
				return true;
			}
			return false;
		}
		else{
			return false;
		}
	}
	public PieceType getType(int x, int y){
		return tabuleiro[x][y].getPiece().getType();
	}
	public int getTeam(int x, int y){
		return tabuleiro[x][y].getPiece().getTeam();
	}
}
