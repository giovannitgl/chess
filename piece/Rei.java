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

	public void updatePosition(int x, int y){
    validMoves.clearAll();
    // Sul
    if( x + 1 < 8 ) {
      evaluatePosition(x + 1, y);
    }
    // Norte
    if( x - 1 >= 0 ) {
      evaluatePosition(x - 1, y);
    }
    // Direita
    if( y + 1 < 8 ) {
      evaluatePosition(x, y + 1);
    }
    // Esquerda
    if( y - 1 >= 0 ) {
      evaluatePosition(x, y - 1);
    }
    // Noroeste
    if( x - 1 >= 0 && y - 1 >= 0 )  {
      evaluatePosition(x - 1, y - 1);
    }
    // Nordeste
    if( x - 1 >= 0 && y + 1 < 8 )  {
      evaluatePosition(x - 1, y + 1);
    }
    // Sudoeste
    if( x + 1 >= 0 && y - 1 >= 0 )  {
      evaluatePosition(x + 1, y - 1);
    }
    // Sudeste
    if( x + 1 >= 0 && y + 1 < 8 )  {
      evaluatePosition(x + 1, y + 1);
    }
	//todo
	}

	public void evaluatePosition(int x, int y){
    validMoves.add( new Point(x,y) );
	}
}