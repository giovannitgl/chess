package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Rainha extends Piece{

  public enum Direction{
    Norte, Sul, Oeste, Leste, Nordeste, Sudeste, Noroeste, Sudoeste
  }

	public Rainha(int x, int y, int time){
		super(x, y, time);
		this.setTeam(time);
		this.pt = PieceType.QUEEN;
		if(time == 0){}//Time debaixo
		else{} //Time de cima
	}

  public void updatePosition(int x, int y){
    validMoves.clear();
    // Sul
    if( x + 1 < 8 ) {
      evaluatePosition(x + 1, y, Direction.Sul);
    }
    // Norte
    if( x - 1 >= 0 ) {
      evaluatePosition(x - 1, y, Direction.Norte);
    }
    // Direita
    if( y + 1 < 8 ) {
      evaluatePosition(x, y + 1, Direction.Leste);
    }
    // Esquerda
    if( y - 1 >= 0 ) {
      evaluatePosition(x, y - 1, Direction.Oeste);
    }
    // Noroeste
    if( x - 1 >= 0 && y - 1 >= 0 )  {
      evaluatePosition(x - 1, y - 1, Direction.Noroeste);
    }
    // Nordeste
    if( x - 1 >= 0 && y + 1 < 8 )  {
      evaluatePosition(x - 1, y + 1, Direction.Nordeste);
    }
    // Sudoeste
    if( x + 1 >= 0 && y - 1 >= 0 )  {
      evaluatePosition(x + 1, y - 1, Direction.Sudoeste);
    }
    // Sudeste
    if( x + 1 >= 0 && y + 1 < 8 )  {
      evaluatePosition(x + 1, y + 1, Direction.Sudeste);
    }
  //todo
  }

	public void evaluatePosition(int x, int y, Direction direction){
	  validMoves.add( new Point(x,y) );
    switch(direction) {
      case Sul:
      if(x + 1 < 8) {
        evaluatePosition(x + 1, y, direction.Sul);
      }
      break;
      case Norte:
      if( x - 1 >= 0 ) {
        evaluatePosition(x - 1, y, direction.Norte);
      }
      break;
      case Leste:
      if( y + 1 < 8 ) {
        evaluatePosition(x, y + 1, direction.Leste);
      }
      break;
      case Oeste:
      if( y - 1 >= 0 ) {
        evaluatePosition(x, y - 1, direction.Oeste);
      }
      break;
      case Noroeste:
      if( x - 1 >= 0 && y - 1 >= 0 )  {
        evaluatePosition(x - 1, y - 1, direction.Noroeste);
      }
      break;
      case Nordeste:
      if( x - 1 >= 0 && y + 1 < 8 )  {
        evaluatePosition(x - 1, y + 1, direction.Nordeste);
      }
      break;
      case Sudoeste:
      if( x + 1 >= 0 && y - 1 >= 0 )  {
        evaluatePosition(x + 1, y - 1, direction.Sudoeste);
      }
      break;
      case Sudeste:
      if( x + 1 >= 0 && y + 1 < 8 )  {
        evaluatePosition(x + 1, y + 1, direction.Sudeste);
      }
      break;
    }
	}

}
