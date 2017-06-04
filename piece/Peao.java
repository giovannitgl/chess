package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Peao extends Piece{

	private boolean firstMove;
	public Peao(int x, int y, int time){
		super(x, y, time);
		this.pt = PieceType.PAWN;
		this.setTeam(time);
		firstMove = true;
		// System.out.println("TIME" + this.team);
		if(time == 0){}//Time debaixo
	}

	public void updatePosition(int x, int y){
		validMoves.clear();
		System.out.println("FIRST = " + firstMove);
		if(firstMove == true){
			if(x+2 < 8){
				evaluatePosition(x+2, y);
			}
			if(x-2 >= 0){
        		evaluatePosition(x-2, y);
			}
			firstMove = false;
		}
		if(x+1 < 8){
      		evaluatePosition(x+1,y);
     		 //desce 1
		}
		if(x-1 >= 0){
			evaluatePosition(x-1,y);
		}
	}

	public void evaluatePosition(int newX, int newY){
		validMoves.add(new Point(newX, newY));
  }
}
