package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Peao extends Piece{
	
	private boolean firstMove;
	public Peao(int x, int y, int time){
		super(x, y, time);
		this.pt = PieceType.PAWN;
		if(time == 0){}//Time debaixo
		else //Time de cima
		firstMove = true;
	}

	public void updatePosition(int x, int y){
		validMoves.clear();

		if(firstMove == true){
			if(x+2 < 8){
				//desceu 2
				evaluatePosition(x+2, y);
			}

			if(x-2 >= 0){
				//subiu 2
			}
			firstMove = false;
		}

		if(x+1 < 8){
			//desce 1
		}

		if(x-1 >= 0){
			//sobe 1
		}
	}

	public void evaluatePosition(int newX, int newY){
		if (true)//Se a posicao que se deseja mover tem uma peca inimiga
			validMoves.add(new Point(newX, newY));
		//Or if the space is empty
		else if(false)//Se a posicao que se deseja mover nao possui peca
			validMoves.add(new Point(newX, newY));
	}
}