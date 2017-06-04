package piece;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;

public class Peao extends Piece{

	private boolean firstMove;
	public Peao(int x, int y, int time){
		super(x, y, time);
		this.pt = PieceType.PAWN;
		this.setTeam(time);
		firstMove = true;
		// System.out.println("TIME" + this.team);
		if(time == 0){}//Time debaixo
	}

	public void updatePosition(ArrayList<Piece> friend, ArrayList<Piece> enemy){

		if(this.getTeam() == 1){
			//black pieces
			Point pt = this.getLocation();
			if(firstMove){
				Point aux = new Point((int)pt.getX()+2, (int)pt.getY());
				if(!this.contains(enemy, aux) && !this.contains(friend,aux)){
					validMoves.add(aux);
				}
			}
			int auxX = (int)pt.getX()+1; 
			int auxY = (int)pt.getY();
			Point aux = new Point(auxX, auxY);
			if(auxX < 8 && !this.contains(enemy, aux) && !this.contains(friend,aux)){
				//square directly downward
				validMoves.add(aux);
			}
			auxY -= 1; //left square downward
			aux.setLocation(auxX,auxY);
			if(auxY >= 0 && auxX < 8 && this.contains(enemy, aux)){
				validMoves.add(aux);
			}
			auxY += 2; //right square downward
			aux.setLocation(auxX, auxY);
			if(auxY < 8 && auxX < 8 && this.contains(enemy, aux)){
				validMoves.add(aux);
			}

		}
		else{
			//white pieces
			Point pt = this.getLocation();
			if(firstMove){
				Point aux = new Point((int)pt.getX()-2, (int)pt.getY());
				if(!this.contains(enemy, aux) && !this.contains(friend,aux)){
					validMoves.add(aux);
				}
			}
			int auxX = (int)pt.getX()-1; 
			int auxY = (int)pt.getY();
			Point aux = new Point(auxX, auxY);
			if(auxX >= 0 && !this.contains(enemy,aux) && !this.contains(friend,aux)){
				//square directly forward
								System.out.println("piece:" + this.getLocX() + this.getLocY() + " valid move " + auxX + auxY);

				validMoves.add(aux);
			}
			auxY -= 1; //left square forward
			aux.setLocation(auxX,auxY);
			if(auxY >= 0 && auxX >= 0 && this.contains(enemy, aux)){
				validMoves.add(aux);
			}
			auxY += 2; //right square forward
			aux.setLocation(auxX, auxY);
			if(auxY < 8 && auxX >= 0 && this.contains(enemy, aux)){
				System.out.println("piece:" + this.getLocX() + this.getLocY() + " valid move " + auxX + auxY);
				validMoves.add(aux);
			}
		}
	}
	private boolean contains(ArrayList<Piece> p, Point pt){
		for(Piece aux : p){
			if (aux.getLocation().equals(pt)){
				return true;
			}
		}
		return false;
	}
}
