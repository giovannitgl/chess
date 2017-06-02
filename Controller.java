import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import piece.*;

public class Controller implements MouseListener{
	private Model m;
	private int mode;
	public void setModel(Model m){
		this.m = m;
	}
	public void setMode(int x){
		this.mode = x;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(mode == 0){
			MenuText text = (MenuText)arg0.getSource();
			int x = text.getRelativeX();
			System.out.println("x" + x);
			if(x == 0){
				m.clickedMenu(x);
				this.setMode(1);
				
			}
		}
		// BoardPanel panel = (BoardPanel)arg0.getSource();
		// int x = panel.getRelativeX();
	 //    int y = panel.getRelativeY();
	 //    System.out.println("X" + x + "Y" + y);
	 //    m.clickedPanel(x,y);
	}
	/**
     * Not implemented
     */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
     * Not implemented
     */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
     * Not implemented
     */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
     * Not implemented
     */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

