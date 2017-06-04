package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Torre extends Piece{

	public enum Direction{
		Norte, Sul, Esquerda, Direita
	}

	public Torre(int x, int y, int time){
		super(x, y, time);
		this.pt = PieceType.ROOK;
		this.setTeam(time);
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

	public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
    validMoves.clear();
    Point start = getLocation();
    Point aux;
    //Norte -> Sul
    for (int i = (int)start.getX() + 1; i < 8; i++) {
      aux = new Point( i, (int)start.getY() );
      if( contains(friend, aux) ) {
        break;
      }
      else if( contains(enemy, aux) ) {
        validMoves.add(aux);
        break;
      }
      else {
        validMoves.add(aux);
      }
    }
    //Sul -> Norte
    for (int i = (int)start.getX() - 1; i >= 0; i--) {
      aux = new Point( i, (int)start.getY() );
      if( contains(friend, aux) ) {
        break;
      }
      else if( contains(enemy, aux) ) {
        validMoves.add(aux);
        break;
      }
      else {
        validMoves.add(aux);
      }
    }
    //Oeste -> Leste
    for (int i = (int)start.getY() + 1; i < 8; i++) {
      aux = new Point( (int)start.getX(), i );
      if( contains(friend, aux) ) {
        break;
      }
      else if( contains(enemy, aux) ) {
        validMoves.add(aux);
        break;
      }
      else {
        validMoves.add(aux);
      }
    }
    //Leste -> Oeste
    for (int i = (int)start.getY() - 1; i >= 0; i--) {
      aux = new Point( (int)start.getX(), i );
      if( contains(friend, aux) ) {
        break;
      }
      else if( contains(enemy, aux) ) {
        validMoves.add(aux);
        break;
      }
      else {
        validMoves.add(aux);
      }
    }
	}
}
