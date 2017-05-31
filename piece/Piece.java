package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Piece{
	protected Point location;
	protected int team;
	protected ArrayList<Point> validMoves;
	protected JLabel icon;

	Piece(int x, int y, int time){
		team = time;
		location  = new Point(x, y);
		validMoves = new ArrayList<Point>();
	}

	public void updatePosition(int x, int y){
		return;
	}

	public void evaluatePosition(int x, int y){
		return;
	}
}