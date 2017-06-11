package gui;
import javax.swing.JPanel;
import java.lang.InterruptedException;

public class RenderWorker implements Runnable{
	JPanel p;
	public RenderWorker(JPanel p){
		this.p = p;
	}
	public void run(){
		while(true){
			// System.out.println("Thread");
			p.repaint();
			try{
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
}