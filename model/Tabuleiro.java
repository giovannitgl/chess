package model;
import gui.*;
import jogador.*;
import piece.*;
import java.awt.Point;
import java.util.ArrayList;

public class Tabuleiro{
	Jogador j0;
	Jogador j1;
	AI_Player AI;
	int mode;
	
	private int bestMoveX = 0, bestMoveY = 0;
	private Piece bestPiece = null;

	private Piece lastPieceMoved;
	private Piece lastPieceTaken;
	private Point lastPoint;
	// Point
	// protected Piece[][] tabuleiro;
	protected Space[][] tabuleiro;
	Tabuleiro(int x){
		tabuleiro = new Space[8][8];
		buildTabuleiro(x);
		setMode(x);
		lastPieceTaken = null;
		lastPieceMoved = null;
		lastPoint = null;
	}

	private void setMode(int mode){
		this.mode = mode;
	}

	public void buildTabuleiro(int x){
		if(x == 0){
			buildSingle();
			buildWhite();
			buildBlack();
			buildMiddle();
			updateValidMove();
		}
		else if(x == 1){
			buildAI();
			buildWhite();
			buildAIPieces();
			buildMiddle();
			AIupdateValidMove();
		}
		
	}

	public void buildSingle(){
		j0 = new Jogador(0);
		j1 = new Jogador(1);
	}

	public void buildAI(){
		j0 = new Jogador(0);
		AI = new AI_Player(1);
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

	private void buildAIPieces(){
		for(int i = 0; i < 8; i++){
			// Peao p = new Peao(1,i,1);
			tabuleiro[1][i] = new Space(new Peao(1,i,1));
			AI.addPiece(tabuleiro[1][i].getPiece());
		}

		// Torre t1 = new Torre(0,0,1);
		// Torre t2 = new Torre(0,7,1);
		tabuleiro[0][0] = new Space(new Torre(0,0,1));
		tabuleiro[0][7] = new Space(new Torre(0,7,1));
		AI.addPiece(tabuleiro[0][0].getPiece());
		AI.addPiece(tabuleiro[0][7].getPiece());

		// Cavalo c1 = new Cavalo(0,1,1);
		// Cavalo c2 = new Cavalo(0,6,1);
		tabuleiro[0][1] = new Space(new Cavalo(0,1,1));
		tabuleiro[0][6] = new Space(new Cavalo(0,6,1));
		AI.addPiece(tabuleiro[0][1].getPiece());
		AI.addPiece(tabuleiro[0][6].getPiece());

		// Bispo b1 = new Bispo(0,2,1);
		// Bispo b2 = new Bispo(0,5,1);
		tabuleiro[0][2] = new Space(new Bispo(0,2,1));
		tabuleiro[0][5] = new Space(new Bispo(0,5,1));
		AI.addPiece(tabuleiro[0][2].getPiece());
		AI.addPiece(tabuleiro[0][5].getPiece());

		tabuleiro[0][3] = new Space(new Rainha(0,3,1));
		tabuleiro[0][4] = new Space(new Rei(0,4,1));
		AI.addPiece(tabuleiro[0][3].getPiece());
		AI.addPiece(tabuleiro[0][4].getPiece());
	}
	private void buildMiddle(){
		for(int i = 2; i < 6; i ++){
			for(int j = 0; j < 8; j++){
				tabuleiro[i][j] = new Space();
			}
		}
	}

	public void changePosition(int x, int y, Piece p){
		lastPoint = null;
		lastPieceTaken = null;
		lastPieceMoved = p;
		int x1 = p.getLocX();
		int y1 = p.getLocY();
		lastPoint = new Point(x1, y1);
		if(tabuleiro[x][y].getPiece()!=null){
			System.out.println("ENTREI NESSA BIROSKA");
			lastPieceTaken = tabuleiro[x][y].getPiece();
		}
		removePiece(x,y);
		tabuleiro[x][y].setPiece(p);
		tabuleiro[x1][y1].setPiece(null);
		System.out.println("REMOVI PECA");
		System.out.println("X = " + x1 + " Y = " + y1);
		p.setLocation(x,y);
		if(mode == 0){
			updateValidMove();
		}
		else if(mode == 1){
			AIupdateValidMove();
		}
	}

	private void undoMove(int index){
		removePiece((int)lastPieceMoved.getLocation().getX(), (int)lastPieceMoved.getLocation().getY());	
		if(lastPieceTaken!=null){//se movimento comeu alguma peca
			System.out.println("Entrei aqui gente!!!");
			addPiece(lastPieceTaken, (int)lastPieceMoved.getLocation().getX(), (int)lastPieceMoved.getLocation().getY());
		}
		// System.out.print("LP = ");
		// System.out.print("X = " + lastPieceMoved.getLocation().getX());
		// System.out.println(" Y = " + lastPieceMoved.getLocation().getY());
		// System.out.println();
		addPiece(lastPieceMoved, (int)lastPoint.getX(), (int)lastPoint.getY(), index);
		// System.out.print("LAST Point = ");
		// System.out.print("X = " + lastPoint.getX());
		// System.out.println(" Y = " + lastPoint.getY());
		
		System.out.println(lastPieceMoved.getType());

		AIupdateValidMove();
	}

	private void addPiece(Piece piece, int x, int y, int index)
	{
		tabuleiro[x][y].setPiece(piece);
		piece.setLocation(x,y);
		if (piece.getTeam()== 1){
			AI.addPiece(index, piece);
		}
		else{
			j0.addPiece(index, piece);
		}
		if(mode == 0){
			updateValidMove();
		}
		else{
			AIupdateValidMove();
		}
	}

	private void addPiece(Piece piece, int x, int y){
		tabuleiro[x][y].setPiece(piece);
		System.out.println("X peca = " + x + " Y peca = " + y);
		piece.setLocation(x,y);
		if (piece.getTeam()== 1){
			//System.out.println("Entrei errado gentii");
			AI.addPiece(piece);
		}
		else{
			System.out.println("Entrei certo aqui para time = " + piece.getTeam());
			j0.addPiece(piece);
		}

		if(mode == 0){
			updateValidMove();
		}
		else{
			AIupdateValidMove();	
		}
	}



	private void updateValidMove(){
		for(int i = 0; i < 8; i++){
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

	private void AIupdateValidMove(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Piece p = tabuleiro[i][j].getPiece();
				if(p != null){
					if(p.getTeam() == 0){
						p.updatePosition(j0.getPlayerPieces(), AI.getPlayerPieces());
					}
					else p.updatePosition(AI.getPlayerPieces(), j0.getPlayerPieces());
					// tabuleiro[i][j].getPiece().updatePosition(i,j);
				}
			}
		}
	}


	public void removePiece(int x, int y)
	{
		Piece piece = tabuleiro[x][y].getPiece();
		if (piece == null)
			return;
		if (piece.getTeam()== 0)
			j0.removePiece(piece);
		else if(mode == 0){
			j1.removePiece(piece);
		}
		else if(mode == 1){
			AI.removePiece(piece);
		}
		tabuleiro[x][y].setPiece(null);
		if(mode == 0){
			updateValidMove();
		}
		else{
			AIupdateValidMove();	
		}
	}


	public boolean isValid(int newX, int newY, Piece p){
    	// p.updatePosition(p.getLocX(),p.getLocY());
    	if(tabuleiro[newX][newY].getPiece() != null){
    		System.out.println("NEW X =" + newX + " NEW Y = " + newY + " TEAMMM = " + tabuleiro[newX][newY].getPiece().getTeam());
    	}
    	System.out.println("ACT X =" + p.getLocX() + " ACT Y = " + p.getLocY() + " TIME = " + p.getTeam());
    	
		ArrayList<Point> validMoves = p.validMoves;

		for(int i = 0; i < validMoves.size(); i++){
			System.out.println("x = " + validMoves.get(i).getX() + " y = " + validMoves.get(i).getY());
		}

		// Point ponto;
		// for(int i = 0; i < validMoves.size(); i++){
		// 	ponto = validMoves.get(i);
		// 	if(newX == (int)ponto.getX() && newY == (int)ponto.getY()){
		// 		//changePosition(x,y,p);
		// 		return true;
		// 	}
		// }
		// return false;
		if(validMoves.contains(new Point(newX,newY))){
			if(p.getType() == PieceType.PAWN)
				p.setFirstMove(false);
			return true;
		}
		else return false;
	}
	public boolean pieceExists(int x, int y){
		if (tabuleiro[x][y] == null)
			return false;
		else
			return true;
	}	
	public boolean isPlayerPiece(int x, int y,int p){
		if(tabuleiro[x][y].getPiece() != null){
			System.out.println("ENTREI NA METADE para time = " + tabuleiro[x][y].getPiece().getTeam());
			if(tabuleiro[x][y].getPiece().getTeam() == p){
				System.out.println("ENTREI INTEIRO PARA TIME = " + p);
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

	public void bestMove(int x, int y){
		int[][] positionWeight = AI.getPositionWeight();
		ArrayList<Piece> AI_pieces = AI.getPlayerPieces();
		ArrayList<Piece> j0_pieces = j0.getPlayerPieces();
		int newWhiteScore = 0;//Jogador
		int maxWhiteScore = 9999999;

		int newBlackScore = 0;//AI
		int maxBlackScore = 0;

		int blackX = 0, blackY = 0;
		Piece blackPiece = null;

		int whiteX = 0, whiteY = 0;

		int totalScore = 0;
		int bestMoveX = 0, bestMoveY = 0;
		Piece bestPiece = null;

		for(int i = 0; i < AI_pieces.size(); i++){
			//int x1 = (int)AI_pieces.get(i).getLocation().getX();
			//int y1 = (int)AI_pieces.get(i).getLocation().getY();
			//if(x == x1 && y == y1){
			//if(isPlayerPiece((int)AI_pieces.get(i).getLocation().getX(), (int)AI_pieces.get(i).getLocation().getY(), 1)){				
				for(int j = 0; j < AI_pieces.get(i).validMoves.size(); j++){
					System.out.println("Size = " + AI_pieces.get(i).validMoves.size());
					int newX = (int)AI_pieces.get(i).validMoves.get(j).getX();
					int newY = (int)AI_pieces.get(i).validMoves.get(j).getY();
					// System.out.print("X = " + AI_pieces.get(i).validMoves.get(j).getX());
					// System.out.println(" Y = " + AI_pieces.get(i).validMoves.get(j).getY());
					// System.out.println(" TYPE = " + AI_pieces.get(i).getType());
					// System.out.println("ENTREI CU!");
					if(tabuleiro[newX][newY].getPiece() != null){
						newBlackScore += AI.getPieceScore(tabuleiro[newX][newY].getPiece().getType());
					}
					newBlackScore += positionWeight[newX][newY];
					if(newBlackScore > maxBlackScore){
						maxBlackScore = newBlackScore;
						blackX = newX;
						blackY = newY;
						blackPiece = AI_pieces.get(i);
						System.out.println("ACHEI UMA PECA DO CARILHOOOOOOOOO");
					}
					changePosition(newX, newY, AI_pieces.get(i));
					for(int m = 0; m < j0_pieces.size(); m++){
						for(int n = 0; n < j0_pieces.get(m).validMoves.size(); n++){
							int newXp = (int)j0_pieces.get(m).validMoves.get(n).getX();
							int newYp = (int)j0_pieces.get(m).validMoves.get(n).getY();
							System.out.print("X = " + j0_pieces.get(m).validMoves.get(n).getX());
							System.out.println(" Y = " + j0_pieces.get(m).validMoves.get(n).getY());
							System.out.println(" TYPE = " + j0_pieces.get(m).getType());
							if(tabuleiro[newXp][newYp].getPiece() != null && tabuleiro[newXp][newYp].getPiece().getTeam() == 1){
								newWhiteScore += AI.getPieceScore(tabuleiro[newXp][newYp].getPiece().getType());
								System.out.println(" COMI A PECINHA DO VIADINHO = " + tabuleiro[newXp][newYp].getPiece().getType());
							}
							newWhiteScore += positionWeight[newXp][newYp];
							System.out.println("------------------------MISTERIO DO WHITE SCORE = " + newWhiteScore);
							
							if(newWhiteScore < maxWhiteScore){
								maxWhiteScore = newWhiteScore;
								whiteX = newXp;
								whiteY = newYp;
							}
							newWhiteScore = 0;
						}
					}
					System.out.println("SCORES: -----------------------------------------");
					System.out.println("BLACK SCORE = " + newBlackScore);
					System.out.println("WHITE SCORE = " + newWhiteScore);
					if(totalScore < (maxBlackScore - maxWhiteScore)){
						totalScore = maxBlackScore - maxWhiteScore;
						bestMoveX = blackX;
						bestMoveY = blackY;
						bestPiece = blackPiece;
					}
					undoMove(i);
					System.out.println("SCOREE = " + newBlackScore);
					newWhiteScore = 0;
					newBlackScore = 0;
				}
				maxWhiteScore = 999999;
				maxBlackScore = 0;
					//return;
		//	}
				System.out.println();
			//}
		}
		System.out.println("BEST X = " + bestMoveX + " BEST Y = " + bestMoveY);
		System.out.print("PIECE X = " + bestPiece.getLocation().getX());
		System.out.println(" PIECE Y = " + bestPiece.getLocation().getY());
		//changePosition(bestMoveX, bestMoveY, bestPiece);
		this.bestMoveX = bestMoveX;
		this.bestMoveY = bestMoveY;
		this.bestPiece = bestPiece;
	}

	public Piece getBestPiece(){
		return this.bestPiece;
	}

	public int getBestX(){
		return this.bestMoveX;
	}

	public int getBestY(){
		return this.bestMoveY;
	}
}
