package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Piece{
	protected Point location;
	protected PieceType pt;
	protected int team;
	public ArrayList<Point> validMoves;
	protected JLabel icon;

	public Piece(int x, int y, int time){
		team = time;
		location  = new Point(x, y);
		validMoves = new ArrayList<Point>();
	}

	public Piece(){
		team = 0;
		icon = null;
		validMoves = null;
		location = null;
	}

	public void updatePosition(int x, int y){
		return;
	}

	public void evaluatePosition(int x, int y){
		return;
	}

	public void deletePiece(){
		team = 0;
		icon = null;
		validMoves = null;
		location = null;
	}

	public int getLocX(){
		return (int)location.getX();
	}

	public int getLocY(){
		return (int)location.getY();
	}

	public void setLocation(int x, int y){
		location.setLocation(x,y);
	}
	public int getTeam(){
		return this.team;
	}
}