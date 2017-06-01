package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Bispo extends Piece{
	
	public enum Direction{
		Nordeste, Sudeste, Noroeste, Sudoeste
	}

	public Bispo(int x, int y, int time){
		super(x, y, time);
		this.pt = PieceType.BISHOP;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

	public void updatePosition(int x, int y){

		if(x-1 >= 0 && y-1 >= 0){
			evaluatePosition(x-1,y-1, Direction.Noroeste);
		}

		if(x+1 < 8 && y-1 >= 0){
			evaluatePosition(x+1, y-1, Direction.Sudoeste);
		}

		if(x-1 >= 0 && y+1 < 8){
			evaluatePosition(x-1, y+1, Direction.Nordeste);
		}

		if(x+1 > 8 && y+1 > 8){
			evaluatePosition(x+1, y+1, Direction.Sudeste);
		}	
	}

	public void evaluatePosition(int x, int y, Direction direction){
		if(true)//se existe uma peca inimiga
			validMoves.add(new Point(x, y));
		else if(false){//se nao existe inimigo mas a posicao esta vazia
			validMoves.add(new Point(x, y));
			switch(direction){
				case Noroeste:
					if(x-1 >= 0 && y-1 >= 0){
						x = x-1;
						y = y-1;
						evaluatePosition(x,y,direction);
					}
					break;

				case Nordeste:
					if(x-1 >= 0 && y+1 < 8){
						x = x-1;
						y = y+1;
						evaluatePosition(x,y,direction);
					}
					break;

				case Sudoeste:
					if(x+1 < 8 && y-1 >= 0){
						//validMoves.add(new Point(x+1, y-1));
						x = x+1;
						y = y-1;
						evaluatePosition(x,y,direction);
					}
					break;

				case Sudeste:
					if(x+1 < 8 && y+1 < 8){
						//validMoves.add(new Point(x+1,y+1));
						x = x+1;
						y = y+1;
						evaluatePosition(x,y,direction);
					}
					break;

				default:
					System.out.println("Direcao Invalida");
			}
		}
	}
}