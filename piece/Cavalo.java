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

	public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
		return;
	}

}