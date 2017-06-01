package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Torre extends Piece{
	
	public enum Direction{
		Norte, Sul, Esquerda, Direita
	}

	public Torre(int x, int y, int time){
		super(x, y, time);
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

	public void updatePosition(int x, int y){
		validMoves.clear();
		
		if(y-1 >= 0){
			evaluatePosition(x,y-1, Direction.Esquerda);
		}

		if(y+1 < 8){
			evaluatePosition(x+1, y, Direction.Direita);
		}

		if(x-1 >= 0){
			evaluatePosition(x-1,y, Direction.Norte);
		}

		if(x+1 < 8){
			evaluatePosition(x+1, y, Direction.Sul);
		}
	}

	public void evaluatePosition(int x, int y, Direction direction){
		if(true)//se existe uma peca inimiga
			validMoves.add(new Point(x, y));
		else if(false){//se nao existe inimigo mas a posicao esta vazia
			validMoves.add(new Point(x, y));
			switch(direction){
				case Norte:
					if(x-1 >= 0){						
						x = x-1;
						evaluatePosition(x,y,direction);
					}
					break;

				case Sul:
					if(x+1 < 8){
						//validMoves.add(new Point(x+1, y));
						x = x+1;
						evaluatePosition(x,y,direction);
					}
					break;

				case Esquerda:
					if(y-1 >= 0){
						//validMoves.add(new Point(x, y-1));
						y = y-1;
						evaluatePosition(x,y,direction);
					}
					break;

				case Direita:
					if(y+1 < 8){
						//validMoves.add(new Point(x,y+1));
						y = y+1;
						evaluatePosition(x,y,direction);
					}
					break;

				default:
					System.out.println("Direcao Invalida");
			}
		}
	}

	public static void main(String[]args){
		Direction d = Direction.Sul;
		switch (d){
			case Norte: 
				System.out.println("Cima");
				break;
			default:
				System.out.println("DD");
				break;
		}
	}
}