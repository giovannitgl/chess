package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Rainha extends Piece{


	public Rainha(int x, int y, int time){
		super(x, y, time);
		this.setTeam(time);
		this.pt = PieceType.QUEEN;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

  public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
    validMoves.clear();
    Point p = this.getLocation();
    Point aux = new Point((int)p.getX(), (int)p.getY());
    int auxX = (int)p.getX();
    int auxY = (int)p.getY();
  
  //esquerda
    for(int i = auxX-1; i >= 0; i--){
      aux.setLocation(i,auxY);
      if(!this.contains(friend,aux)){
        validMoves.add(new Point(i,auxY));
      }
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,auxY));
        break;
      }
    }
  //direita
    for(int i = auxX+1; i < 8; i++){
      aux.setLocation(i,auxY);
      if(!this.contains(friend,aux)){
        validMoves.add(new Point(i,auxY));
      }
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,auxY));
        break;
      }
    }
  //cima
   for(int i = auxY+1; i < 8; i++){
      aux.setLocation(auxX,i);
      if(!this.contains(friend,aux)){
        validMoves.add(new Point(auxX,i));
      }
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(auxX,i));
        break;
      }
    }
  //baixo
    for(int i = auxY-1; i >= 0; i--){
      aux.setLocation(auxX,i);
      if(!this.contains(friend,aux)){
        validMoves.add(new Point(auxX,i));
      }
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(auxX,i));
        break;
      }
    }
  //esquerda + cima
  //esquerda + baixo
  //direita + cima
  //direita + baixo
  }
}
