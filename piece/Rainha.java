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
    for(int i = auxY-1; i >= 0; i--){
      aux.setLocation(auxX,i);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(auxX,i));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(auxX,i));
    }
  //direita
    for(int i = auxY+1; i < 8; i++){
      aux.setLocation(auxX,i);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(auxX,i));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(auxX,i));
    }
  //cima
     for(int i = auxX+1; i < 8; i++){
      aux.setLocation(i,auxY);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,auxY));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(i,auxY));
    }
    //baixo
      for(int i = auxX-1; i >= 0; i--){
      aux.setLocation(i,auxY);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,auxY));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(i,auxY));
    }
    //esquerda + cima
      int i = auxX + 1; // linha
      int j = auxY - 1; // coluna
      for(; i < 8 && j >= 0; i++, j--){
      aux.setLocation(i,j);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,j));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(i,j));
    }
    //esquerda + baixo
      i = auxX - 1; // linha
      j = auxY - 1; // coluna
    for(; i >= 0 && j >= 0; i--, j--){
      aux.setLocation(i,j);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,j));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(i,j));
    }
    //direita + cima
    i = auxX + 1; // linha
    j = auxY + 1; // coluna
    for(; i < 8 && j < 8; i++, j++){
      aux.setLocation(i,j);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,j));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(i,j));
    }
     //direita + baixo
    i = auxX - 1; // linha
    j = auxY + 1; // coluna
    for(; i >= 0 && j < 8; i--, j++){
      aux.setLocation(i,j);
      if(this.contains(enemy,aux)){
        validMoves.add(new Point(i,j));
        break;
      }
      else if(this.contains(friend,aux)){
        break;
      }
      else
        validMoves.add(new Point(i,j));
    }
  }
}
