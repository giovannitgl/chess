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

  public boolean checkKnight(Piece enemy, Point p)  {
    Point aux = enemy.getLocation();
    if (aux.getX() + 2 == p.getX() && aux.getY() - 1 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() + 2 == p.getX() && aux.getY() + 1 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() - 2 == p.getX() && aux.getY() + 1 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() - 2 == p.getX() && aux.getY() - 1 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() + 1 == p.getX() && aux.getY() - 2 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() - 1 == p.getX() && aux.getY() - 2 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() + 1 == p.getX() && aux.getY() + 2 == p.getY()) {
      return false;
    }
    else
    if (aux.getX() - 1 == p.getX() && aux.getY() + 2 == p.getY()) {
      return false;
    }
    return true;
  }

  public boolean checkBishop(Piece pc, Point p)  {
    pc.updatePosition(friend,enemy);
    for (int i = 0; i < pc.validMoves.size() ; i++) {
      if ( pc.validMoves.get(i).getX() == p.getX() && pc.validMoves.get(i).getY() == p.getY()) {
        return false;
      }
    }
    return true;
  }

  public boolean checkRook(Piece pc, Point p) {
    pc.updatePosition(friend,enemy);
    for (int j = 0; j < pc.validMoves.size() ; j++) {
      if ( pc.validMoves.get(j).getX() == p.getX() && pc.validMoves.get(j).getY() == p.getY()) {
        return false;
      }
    }
    return true;
  }

  public boolean checkQueen(Piece pc, Point p) {
    pc.updatePosition(friend,enemy);
    for (int j = 0; j < pc.validMoves.size() ; j++) {
      if ( pc.validMoves.get(j).getX() == p.getX() && pc.validMoves.get(j).getY() == p.getY()) {
        return false;
      }
    }
    return true;
  }

  public boolean isSafe(ArrayList<Piece> friend, ArrayList<Piece> enemy, Point p)  {
    for (int i = 0; i < enemy.size() ; i++ ) {
      Piece pc = enemy.get(i);
      switch(pc.getType()) {
        case PAWN:
          if(checkPawn(pc, p) == false)  {return false;}
        case KNIGHT:
          if(checkKnight(pc,p) == false) {return false;}
        case BISHOP:
          //if(checkBishop(enemy,friend,pc,p) == false) {return false;}
        case ROOK:
          //if(checkRook(enemy,friend,pc,p) == false) {return false;}
        case QUEEN:
          //if(checkQueen(enemy,friend,pc,p) == false) {return false;}
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
    // Norte
    x = (int)start.getX() - 1;
    y = (int)start.getY();
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Sul
    x = (int)start.getX() + 1;
    y = (int)start.getY();
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Oeste
    x = (int)start.getX();
    y = (int)start.getY() - 1;
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Leste
    x = (int)start.getX();
    y = (int)start.getY() + 1;
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Nordeste
    x = (int)start.getX() - 1;
    y = (int)start.getY() + 1;
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Noroeste
    x = (int)start.getX() - 1;
    y = (int)start.getY() - 1;
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Sudeste
    x = (int)start.getX() + 1;
    y = (int)start.getY() + 1;
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }
    // Sudoeste
    x = (int)start.getX() + 1;
    y = (int)start.getY() - 1;
    aux = new Point(x,y);
    if ( isSafe(friend,enemy,aux) ) {
      validMoves.add(aux);
    }

  }
}
