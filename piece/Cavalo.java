package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Cavalo extends Piece{
	
	public Cavalo(int x, int y, int time){
		super(x, y, time);
		this.setTeam(time);
		this.pt = PieceType.KNIGHT;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

	public void updatePosition(int x, int y){
		validMoves.clear();
		
		if(x+1 < 8 && y+2 < 8){
			//desceu x=1
			//direita y=2
			evaluatePosition(x+1,y+2);
		}

		if(x+1 < 8 && y-2 >= 0){
			//desceu x=1
			//esquerda y=2
			evaluatePosition(x+1,y-2);
		}

		if(x-1 >= 0 && y-2 >=0){
			//subiu x=1
			//esquerda y=2
			evaluatePosition(x-1,y-2);
		}

		if(x-1 >= 0 && y+2 < 8){
			//subiu x=1
			//direita y=2
			evaluatePosition(x-1,y+2);
		}

		if(x+2 < 8 && y+1 < 8){
			//desceu x=2
			//direita y=1
			evaluatePosition(x+2,y+1);
		}

		if(x+2 < 8 && y-1 >= 0){
			//desceu x=2
			//esquerda y=1
			evaluatePosition(x+2,y-2);
		}

		if(x-2 >= 0 && y+1 < 8){
			//subiu x=2
			//direita y=1
			evaluatePosition(x-2,y+1);
		}

		if(x-2 >= 0 && y-1 >= 0){
			//subiu x=2
			//esquerda y=1
			evaluatePosition(x-2,y-1);
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