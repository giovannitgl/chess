package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Rei extends Piece{
	
	public enum Direction{
		Nordeste, Sudeste, Noroeste, Sudoeste
	}

	public Rei(int x, int y, int time){
		super(x, y, time);
		this.setTeam(time);
		this.pt = PieceType.KING;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

	public void updatePosition(int x, int y){

	//todo
	}

	public void evaluatePosition(int x, int y, Direction direction){
		//todo	
	}
}