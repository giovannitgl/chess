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
		return;
	}
}