public class Space{
	private Piece piece;
	public getX(){
		return piece.getLocX();
	}
	public getY(){
		return piece.getLocY();
	}
	public setLocation(int x, int y){
		piece.setLocation(x,y);
	}
	public evaluatePosition(int x, int y){
		piece.evaluatePosition(x,y);
	}
	public isPieceNull(){
		if(piece == null)
			return true;
		else return false;
	}
	Space(Piece p){
		this.piece = p;
	}
}