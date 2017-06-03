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
      evaluatePosition(x + 1, y, Sul);
    }
    // Norte
    if( x - 1 >= 0 ) {
      evaluatePosition(x - 1, y, Norte);
    }
    // Direita
    if( y + 1 < 8 ) {
      evaluatePosition(x, y + 1, Leste);
    }
    // Esquerda
    if( y - 1 >= 0 ) {
      evaluatePosition(x, y - 1, Oeste);
    }
    // Noroeste
    if( x - 1 >= 0 && y - 1 >= 0 )  {
      evaluatePosition(x - 1, y - 1, Noroeste);
    }
    // Nordeste
    if( x - 1 >= 0 && y + 1 < 8 )  {
      evaluatePosition(x - 1, y + 1, Nordeste);
    }
    // Sudoeste
    if( x + 1 >= 0 && y - 1 >= 0 )  {
      evaluatePosition(x + 1, y - 1, Sudoeste);
    }
    // Sudeste
    if( x + 1 >= 0 && y + 1 < 8 )  {
      evaluatePosition(x + 1, y + 1, Sudeste);
    }
  //todo
  }

	public void evaluatePosition(int x, int y, Direction direction){
	  validMoves.add( new Point(x,y) );
    switch(direction) {
      Sul:
      if(x + 1 < 8) {
        evaluatePosition(x + 1, y, Sul);
      }
      Norte:
      if( x - 1 >= 0 ) {
        evaluatePosition(x - 1, y, Norte);
      }
      Leste:
      if( y + 1 < 8 ) {
        evaluatePosition(x, y + 1, Leste);
      }
      Oeste:
      if( y - 1 >= 0 ) {
        evaluatePosition(x, y - 1, Oeste);
      }
      Noroeste:
      if( x - 1 >= 0 && y - 1 >= 0 )  {
        evaluatePosition(x - 1, y - 1, Noroeste);
      }
      Nordeste:
      if( x - 1 >= 0 && y + 1 < 8 )  {
        evaluatePosition(x - 1, y + 1, Nordeste);
      }
      Sudoeste:
      if( x + 1 >= 0 && y - 1 >= 0 )  {
        evaluatePosition(x + 1, y - 1, Sudoeste);
      }
      Sudeste:
      if( x + 1 >= 0 && y + 1 < 8 )  {
        evaluatePosition(x + 1, y + 1, Sudeste);
      }
    }
	}
}
