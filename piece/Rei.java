package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Rei extends Piece{

	public Rei(int x, int y, int time){
		super(x, y, time);
		this.setTeam(time);
		this.pt = PieceType.KING;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

  public boolean checkPawn(Piece enemy, Point p) {
    Point aux = enemy.getLocation();
    if(aux.getX() + 1 == p.getX() && aux.getY() + 1 == p.getY()) {
      return false;
    }
    else
    if(aux.getX() + 1 == p.getX() && aux.getY() - 1 == p.getY()) {
      return false;
    }
    else
    if(aux.getX() - 1 == p.getX() && aux.getY() + 1 == p.getY()) {
      return false;
    }
    else
    if(aux.getX() - 1 == p.getX() && aux.getY() - 1 == p.getY()) {
      return false;
    }
    return true;
  }

  public boolean
  public boolean isSafe(ArrayList<Piece> enemy, Point p)  {
    for (int i = 0; i < enemy.size() ; i++ ) {
      Piece pc = enemy.get(i);
      switch(pc.getType()) {
        case PAWN:
          if(checkPawn(pc, p) == false)  {return false;}
        case KNIGHT:

        case BISHOP:

        case ROOK:

        case QUEEN:

        case KING:
      }
    }
    return true;
  }

  public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
    validMoves.clear();
    Point start = getLocation();
    Point aux;
    int x, y;
    ArrayList<Point> temp;
    // Norte -> Sul
    x = (int)start.getX() + 1;
    y = (int)start.getY();
    aux = new Point(x,y);
    if ( isSafe(enemy,aux) ) {
      validMoves.add(aux);

    }
    // Sul -> Norte
    x = (int)start.getX() - 1;
    y = (int)start.getY();
    aux = new Point(x,y);
    if ( isSafe(enemy,aux) ) {
      validMoves.add(aux);

    }


  }
}
