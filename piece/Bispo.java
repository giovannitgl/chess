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
		this.setTeam(time);
		this.pt = PieceType.BISHOP;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

	public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
		return;
	}

}