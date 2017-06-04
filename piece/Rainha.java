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

  public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){
    return;
  }

}
