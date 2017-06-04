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

	public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
		return;
	}
}
